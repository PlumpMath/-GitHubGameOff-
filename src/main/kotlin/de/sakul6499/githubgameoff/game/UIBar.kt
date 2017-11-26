package de.sakul6499.githubgameoff.game

import de.sakul6499.githubgameoff.engine.Engine
import de.sakul6499.githubgameoff.engine.GameMain
import de.sakul6499.githubgameoff.engine.graphics.Layer
import de.sakul6499.githubgameoff.engine.graphics.Screen
import de.sakul6499.githubgameoff.engine.input.MouseHandler
import de.sakul6499.githubgameoff.engine.maths.BoxVector
import de.sakul6499.githubgameoff.engine.maths.Vector2I
import java.awt.Color
import java.awt.Graphics

object UIBar : Layer("MenuUI", 1, Vector2I(0, 0), Vector2I(Screen.GetWidth(), 48)) {
    override val isActive: Boolean = true

    val amountTilesX = 48
    val amountTilesY = 2
    val sizeX = (getWidth() - 256) / amountTilesX
    val sizeY = getHeight() / amountTilesY

    private val heartSize = 32
    private val maxHearts = 10
    private var hearts = 3

    val button: UIButton = object : UIButton(BoxVector(Vector2I(getWidth() - sizeX * 2, 0), Vector2I(getWidth(), sizeY))) {
        private var hover = false

        override fun update(delta: Long, alpha: Long) {
            hover = vb.isInBox(Vector2I(MouseHandler.MousePosition.x, MouseHandler.MousePosition.y))

            if (hover && MouseHandler.MouseButtonLeftPressed) {
                GameMain.pauseGame(true)
            }
        }

        override fun render(graphics: Graphics) {
            if (hover) Engine.renderRectangle(graphics, vb.v0.x, vb.v0.y, vb.v1.x - vb.v0.x, vb.v1.y, Color.BLUE)
            Engine.renderRectangle(graphics, vb.v0.x + (sizeX / 4), sizeY / 2, sizeX, 2, Color.WHITE)
        }
    }

    override fun update(delta: Long, alpha: Long) {
        button.update(delta, alpha)
    }

    override fun render(graphics: Graphics) {
        renderRectangle(graphics, getStartX(), getStartY(), getWidth(), getHeight(), color = Color.BLACK)

        /*
        Width [1280] / AmountTiles [32] = 40px
         */


        for (y in 0 until amountTilesY) {
            for (x in 0 until amountTilesX) {
                graphics.color = Color.RED
                graphics.drawRect(x * sizeX, y * sizeY, sizeX, sizeY)
            }
        }

        button.render(graphics)

//        val size = 32
//        // TODO ... ?
//        for(x in 0 until (getWidth() / size) - 1) {
//            for(y in 0 until 1) {
//                graphics.color = Color.RED
//                graphics.drawRect(x * size, y * size, size, size)
//
//                println("$x $y")
//            }
//        }

//        if(hearts < 10) {
//            for(x in 0 until hearts) {
//                graphics.color = Color.RED
//                graphics.fillOval(16 + ((x * heartSize)), heartSize / 4, heartSize, heartSize)
//            }
//        } else {
//            // ...
//        }
    }
}