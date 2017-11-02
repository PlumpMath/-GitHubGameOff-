package de.sakul6499.githubgameoff.gui

import de.sakul6499.githubgameoff.GameMain
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage

abstract class GUIBasicTextElement(x: Double, y: Double, sizeX: Double, sizeY: Double, var text: String? = null, var font: Font = GameMain.gameConfig.getValidFont()) : GUIBasicElement(x, y, sizeX, sizeY) {
    fun renderText(graphics: Graphics, x: Double, y: Double, sizeX: Double, sizeY: Double, text: String) {
        val bufferedImage = BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB)
        val graphics2D = bufferedImage.graphics as Graphics2D
        graphics2D.color = Color.MAGENTA
        graphics2D.fillRect(0, 0, bufferedImage.width / 2, bufferedImage.height / 2)
        graphics2D.fillRect(bufferedImage.width / 2, bufferedImage.height / 2, bufferedImage.width, bufferedImage.height)
        graphics2D.color = Color.PINK
        graphics2D.fillRect(0, bufferedImage.height / 2, bufferedImage.width / 2, bufferedImage.height)
        graphics2D.fillRect(bufferedImage.width / 2, 0, bufferedImage.width, bufferedImage.height / 2)

        var _x = y.toInt()
        text.forEach {
            graphics.drawImage(bufferedImage, _x, y.toInt(), sizeX.toInt(), sizeY.toInt(), null)
            _x += sizeX.toInt()
        }
    }
}