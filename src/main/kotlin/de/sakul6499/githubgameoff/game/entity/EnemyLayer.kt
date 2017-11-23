package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.graphics.asset.Image
import de.sakul6499.githubgameoff.engine.graphics.asset.Images
import de.sakul6499.githubgameoff.engine.graphics.asset.StagedImage
import de.sakul6499.githubgameoff.engine.graphics.Layer
import de.sakul6499.githubgameoff.engine.maths.Direction
import javafx.stage.Stage
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





        // Old:
//        Images.registerImage("eye", Image(ImageIO.read(this.javaClass.getResource("/ps/eye.png"))))
//        Images.registerImage("eye_pupil", Image(ImageIO.read(this.javaClass.getResource("/ps/eye_pupil.png"))))
//        Images.registerImage("eye_close", StagedImage(arrayOf(
//                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_close_0.png"))),
//                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_close_1.png"))),
//                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_close_2.png"))),
//                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_close_3.png")))
//        )))
//        Images.registerImage("eye_open", StagedImage(arrayOf(
//                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_open_0.png"))),
//                Image(ImageIO.read(this.javaClass.getResource("/ps/eye_open_1.png")))
//        )))
    }

    private var counter = 0
    private var stage = 0


    override fun update(delta: Long, alpha: Long) {
        enemies.forEach {
            it.update(delta, alpha)
//            println(it.direction)
//            num = when(it.direction) {
//                Direction.NULL -> 0
//
//                Direction.LEFT -> 0
//                Direction.UP -> 1
//                Direction.RIGHT -> 2
//                Direction.DOWN -> 3
//
//                Direction.UP_LEFT -> 0
//                Direction.UP_RIGHT -> 2
//                Direction.DOWN_LEFT -> 0
//                Direction.DOWN_RIGHT -> 2
//            }

            if(counter++ > 25) {
                counter = 0
                stage++
                if(stage > 1) stage = 0
            }

//            it.position.add(it.direction.copy().multiply(it.type.speed, it.type.speed))
//            if(!Maths.isInAABB(it.position.x.toInt(), it.position.y.toInt(), BackgroundLayer.getValidRange().first.x, BackgroundLayer.getValidRange().first.y, BackgroundLayer.getValidRange().second.x, BackgroundLayer.getValidRange().second.y)) {
//                bullets.remove(it)
//            } else {
//                // check for player contact
//            }
        }
    }

    override fun render(graphics: Graphics) {
        enemies.forEach { it.render(graphics) }
    }
}