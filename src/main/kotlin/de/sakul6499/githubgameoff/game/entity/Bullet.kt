package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.Renderable
import de.sakul6499.githubgameoff.engine.entity.Entity
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import java.awt.Color
import java.awt.Graphics

class Bullet(position: Vector2F, var direction: Vector2F, var type: BulletType, var player: Player): Entity(Vector2F(position.x, position.y), 0.075F, 0.8F), Renderable {

    enum class BulletType(var color: Color, var size: Int) {
        NORMAL_RED(Color.RED, 15),
        NORMAL_GREEN(Color.GREEN, 15),

        BIG_RED(Color.RED, 50),
        BIG_GREEN(Color.GREEN, 50)
    }

    var i: Int = 0
    override fun update(delta: Long, alpha: Long) {
//        val a = if(alpha <= 1) 1 else alpha
//        movement.add(speedMultiplier * a, speedMultiplier * a)
//        if(!movement.isNull()) position.add(movement)

        position.add(direction)

        if(++i >= 500) {
            println("REMOVED")
            player.bullets.remove(this)
            return
        }
    }

    override fun render(graphics: Graphics) {
        graphics.color = type.color
        graphics.fillOval(position.getRoundX() - type.size / 2, position.getRoundY() - type.size / 2, type.size, type.size)
    }
}