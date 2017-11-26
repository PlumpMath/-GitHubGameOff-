package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.graphics.Layer
import de.sakul6499.githubgameoff.engine.maths.BoxVector
import de.sakul6499.githubgameoff.engine.maths.Maths
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.engine.maths.Vector2I
import de.sakul6499.githubgameoff.game.BackgroundLayer
import java.awt.Color
import java.awt.Graphics
import java.util.concurrent.CopyOnWriteArrayList

object BulletLayer : Layer("Bullet", EnemyLayer.order + 1, BackgroundLayer.v0, BackgroundLayer.v1) {
    data class Bullet(val position: Vector2F, var direction: Vector2F, var type: BulletType) {
        enum class BulletType(val color: Color, val size: Int, val speed: Float, val damagePlayer: Int, val damageEnemy: Int) {
            NORMAL_RED(Color.RED, 15, 0.25F, 0, 25),
            NORMAL_GREEN(Color.GREEN, 15, 0.75F, 25, 0),

            BIG_RED(Color.RED, 50, 0.5F, 0, 50),
            BIG_GREEN(Color.GREEN, 50, 1F, 50, 0)
        }

        fun getBox(): BoxVector<Vector2I> = BoxVector(Vector2I(getStartX(), getStartY()), Vector2I(getEndX(), getEndY()))

        fun getWidth(): Int = getEndX() - getStartX()
        fun getHeight(): Int = getEndY() - getStartY()

        fun getStartX(): Int = position.getRoundX()
        fun getStartY(): Int = position.getRoundY()
        fun getEndX(): Int = getStartX() + type.size
        fun getEndY(): Int = getStartY() + type.size
    }

    internal val bullets: CopyOnWriteArrayList<Bullet> = CopyOnWriteArrayList()
    override var isActive: Boolean = bullets.isNotEmpty()
        private set
        get() {
            field = bullets.isNotEmpty()
            return field
        }

    override fun update(delta: Long, alpha: Long) {
        bullets.forEach { Bullet ->
            Bullet.position.add(Bullet.direction.copy().multiply(Bullet.type.speed, Bullet.type.speed))
            if (!Maths.isInAABB(Bullet.position.x.toInt(), Bullet.position.y.toInt(), BackgroundLayer.v0.x, BackgroundLayer.v0.y, BackgroundLayer.v1.x, BackgroundLayer.v1.y)) {
                bullets.remove(Bullet)
            } else {
                // check for player contact

                if (Bullet.type.damagePlayer > 0) {
                    if (Bullet.getBox().intersect(PlayerLayer.getBox(), true)) {
                        bullets.remove(Bullet)
                        println("HIT PLAYER!")
                    }
                }

                if (Bullet.type.damageEnemy > 0) {
                    EnemyLayer.enemies.filter { Enemy -> Bullet.getBox().intersect(Enemy.getBox(), true) }.forEach { Enemy ->
                        bullets.remove(Bullet)
                        println("HIT ENEMY!")

//                        EnemyLayer.enemies.find { it == Enemy }!!

                        Enemy.health -= Bullet.type.damageEnemy

                        println("Health: ${Enemy.health}")
                    }
                }
            }
        }
    }

    override fun render(graphics: Graphics) {
        bullets.forEach {
            graphics.color = it.type.color
            graphics.fillOval(it.position.x.toInt() - it.type.size / 2, it.position.y.toInt() - it.type.size / 2, it.type.size, it.type.size)
            graphics.fillRect(it.position.x.toInt() - it.type.size / 2, it.position.y.toInt() - it.type.size / 2, it.type.size, it.type.size)
        }
    }
}