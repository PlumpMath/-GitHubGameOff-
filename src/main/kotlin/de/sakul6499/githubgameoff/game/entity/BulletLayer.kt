package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.graphics.Layer
import de.sakul6499.githubgameoff.engine.maths.Maths
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.game.BackgroundLayer
import java.awt.Color
import java.awt.Graphics
import java.util.concurrent.CopyOnWriteArrayList

object BulletLayer : Layer("Bullet", EnemyLayer.order + 1, BackgroundLayer.getValidRange().first.x, BackgroundLayer.getValidRange().first.y, BackgroundLayer.getValidRange().second.x, BackgroundLayer.getValidRange().second.y) {
    data class Bullet(val position: Vector2F, var direction: Vector2F, var type: BulletType) {
        enum class BulletType(var color: Color, var size: Int, var speed: Float) {
            NORMAL_RED(Color.RED, 15, 0.25F),
            NORMAL_GREEN(Color.GREEN, 15, 0.75F),

            BIG_RED(Color.RED, 50, 0.5F),
            BIG_GREEN(Color.GREEN, 50, 1F)
        }
    }

    internal val bullets: CopyOnWriteArrayList<Bullet> = CopyOnWriteArrayList()
    override var isActive: Boolean = bullets.isNotEmpty()
        private set
        get() {
            field = bullets.isNotEmpty()
            return field
        }

    override fun update(delta: Long, alpha: Long) {
        bullets.forEach {
            it.position.add(it.direction.copy().multiply(it.type.speed, it.type.speed))
            if (!Maths.isInAABB(it.position.x.toInt(), it.position.y.toInt(), BackgroundLayer.getValidRange().first.x, BackgroundLayer.getValidRange().first.y, BackgroundLayer.getValidRange().second.x, BackgroundLayer.getValidRange().second.y)) {
                bullets.remove(it)
            } else {
                // check for player contact
            }
        }
    }

    override fun render(graphics: Graphics) {
        bullets.forEach {
            graphics.color = it.type.color
            graphics.fillOval(it.position.x.toInt() - it.type.size / 2, it.position.y.toInt() - it.type.size / 2, it.type.size, it.type.size)
        }
    }
}