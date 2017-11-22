package de.sakul6499.githubgameoff.engine.gui

import de.sakul6499.githubgameoff.engine.asset.SpriteFont
import de.sakul6499.githubgameoff.engine.graphics.Renderable
import java.awt.Graphics

open class Text(var x: Int, var y: Int, _text: String, var fontColor: SpriteFont.FontColor = SpriteFont.FontColor.WHITE, var fontType: SpriteFont.FontType = SpriteFont.FontType.NORMAL, var fontWidth: Int = SpriteFont.tileDimension, var fontHeight: Int = SpriteFont.tileDimension, var fontVerticalSpacing: Int = fontWidth / 2) : Renderable {
    private var text: String = ""
    private var textLength: Int = 0

    init {
        setText(_text)
    }

    override fun render(graphics: Graphics) {
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