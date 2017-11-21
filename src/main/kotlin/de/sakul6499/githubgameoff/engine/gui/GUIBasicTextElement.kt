package de.sakul6499.githubgameoff.engine.gui

import de.sakul6499.githubgameoff.engine.asset.SpriteFont
import java.awt.Graphics

abstract class GUIBasicTextElement(x: Int, y: Int, sizeX: Int, sizeY: Int, var text: String, override var active: Boolean = true) : GUIBasicElement(x, y, sizeX, sizeY, active) {
    fun renderText(graphics: Graphics, x: Int = this.x, y: Int = this.y, width: Int = SpriteFont.tileDimension, height: Int = SpriteFont.tileDimension, spacingY: Int = width / 2, fontType: SpriteFont.FontType = SpriteFont.FontType.NORMAL, fontColor: SpriteFont.FontColor = SpriteFont.FontColor.BLACK) {
        var index = 0
        SpriteFont.getString(text, fontType, fontColor).forEach {
            graphics.drawImage(it, x + (index++ * (spacingY)), y, width, height, null)
        }
    }
}