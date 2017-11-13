package de.sakul6499.githubgameoff.game.gui

import de.sakul6499.githubgameoff.game.Renderable
import de.sakul6499.githubgameoff.game.asset.SpriteFont
import java.awt.Graphics

open class Text(var x: Int, var y: Int, _text: String, var fontColor: SpriteFont.FontColor, var fontType: SpriteFont.FontType, var fontWidth: Int = SpriteFont.tileDimension, var fontHeight: Int = SpriteFont.tileDimension, var fontVerticalSpacing: Int = fontWidth / 2) : Renderable {
    private var text: String = ""
    private var textLength: Int = 0

    init {
        setText(_text)
    }

    override fun render(deltaTime: Double, graphics: Graphics) {
        var index = 0
        SpriteFont.getString(text, fontType, fontColor).forEach {
            graphics.drawImage(it, x + (index++ * (fontVerticalSpacing)), y, fontWidth, fontHeight, null)
        }
    }

    open fun setText(text: String): Int {
        this.text = text
        this.textLength = SpriteFont.getStringLength(text, fontWidth, fontVerticalSpacing)
        return this.textLength
    }
}