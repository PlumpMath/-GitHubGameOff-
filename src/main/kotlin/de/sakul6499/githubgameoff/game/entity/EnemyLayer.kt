package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.asset.Image
import de.sakul6499.githubgameoff.engine.asset.Images
import de.sakul6499.githubgameoff.engine.asset.StagedImage
import de.sakul6499.githubgameoff.engine.graphics.Layer
import java.awt.Graphics
import java.util.concurrent.CopyOnWriteArrayList
import javax.imageio.ImageIO

object EnemyLayer : Layer("Enemy", PlayerLayer.order + 1) {
    internal val enemies: CopyOnWriteArrayList<Enemy> = CopyOnWriteArrayList()
    override var isActive: Boolean = enemies.isNotEmpty()
        private set
        get() {
            field = enemies.isNotEmpty()
            return field
        }

    init {
        Images.registerImage("eye", Image(ImageIO.read(this.javaClass.getResource("/ps/eye.png"))))
        Images.registerImage("eye_pupil", Image(ImageIO.read(this.javaClass.getResource("/ps/eye_pupil.png"))))
        Images.registerImage("eye_close", StagedImage(arrayOf(
                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_close_0.png"))),
                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_close_1.png"))),
                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_close_2.png"))),
                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_close_3.png")))
        )))
        Images.registerImage("eye_open", StagedImage(arrayOf(
                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_open_0.png"))),
                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_open_1.png")))
        )))
    }

    override fun update(delta: Long, alpha: Long) {
        enemies.forEach {
            it.update(delta, alpha)

//            it.position.add(it.direction.copy().multiply(it.type.speed, it.type.speed))
//            if(!Maths.isInAABB(it.position.x.toInt(), it.position.y.toInt(), BackgroundLayer.getValidRange().first.x, BackgroundLayer.getValidRange().first.y, BackgroundLayer.getValidRange().second.x, BackgroundLayer.getValidRange().second.y)) {
//                bullets.remove(it)
//            } else {
//                // check for player contact
//            }
        }
    }

    override fun render(graphics: Graphics) {
        enemies.forEach {
            it.boxVector.drawBox(graphics)

            val w = it.boxVector.v1.x.toInt() - it.boxVector.v0.x.toInt()
            val h = it.boxVector.v1.y.toInt() - it.boxVector.v0.y.toInt()

            renderImage(graphics, Images.getImage("eye"), it.boxVector.v0.x.toInt() - w / 2, it.boxVector.v0.y.toInt() - h / 2, w, h)
            renderImage(graphics, Images.getImage("eye_pupil"), it.boxVector.v0.x.toInt() - (w / 2) / 2, it.boxVector.v0.y.toInt() - (h / 2) / 2, w / 2, h / 2)
        }
//        bullets.forEach {
//            graphics.color = it.type.color
//            graphics.fillOval(it.position.x.toInt() - it.type.size / 2, it.position.y.toInt() - it.type.size / 2, it.type.size, it.type.size)
//        }
    }
}