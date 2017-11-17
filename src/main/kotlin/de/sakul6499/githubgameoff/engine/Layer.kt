package de.sakul6499.githubgameoff.engine

import de.sakul6499.githubgameoff.engine.asset.SpriteFont
import de.sakul6499.githubgameoff.engine.maths.Vector2I
import java.awt.Color
import java.awt.Graphics
import java.awt.Image

abstract class Layer(val name: String, internal var order: Int, internal val x: Int = 0, internal val y: Int = 0, internal val width: Int = Screen.GetWidth(), internal val height: Int = Screen.GetHeight()): Updateable, Renderable {
    abstract val isActive: Boolean

    fun renderRectangle(graphics: Graphics, x: Int = this.x, y: Int = this.y, width: Int = this.width, height: Int = this.height, color: Color = Color.BLACK) {
        val position = translate(x, y, width, height)

        graphics.color = color
        graphics.fillRect(position.x, position.y, width, height)
    }

    fun renderText(graphics: Graphics, text: String, x: Int = this.x, y: Int = this.y, centered: Boolean = false, fontColor: SpriteFont.FontColor = SpriteFont.FontColor.WHITE, fontType: SpriteFont.FontType = SpriteFont.FontType.NORMAL, fontWidth: Int = SpriteFont.tileDimension, fontHeight: Int = SpriteFont.tileDimension, fontVerticalSpacing: Int = fontWidth / 2) {
        val position = translate(x, y)

        var charIndex = 0
        var lineIndex = 0

        val lines: List<String> = if(text.contains('\n')) {
            text.split('\n')
        } else {
            listOf(text)
        }

        if(centered) {
            lines.forEach { line ->
                // Note: no | scaling
                val startX = position.x - SpriteFont.getStringLength(line, fontWidth, fontVerticalSpacing) / 2
                val startY = position.y - fontHeight

                SpriteFont.getString(line, fontType, fontColor).forEach {
                    graphics.drawImage(it, startX + (charIndex++ * fontVerticalSpacing), startY + (fontHeight * lineIndex), fontWidth, fontHeight, null)
                }

                lineIndex++
                charIndex = 0
            }
        } else {
            lines.forEach { line ->
                SpriteFont.getString(line, fontType, fontColor).forEach {
                    graphics.drawImage(it, position.x + (charIndex++ * fontVerticalSpacing), position.y + (fontHeight * lineIndex), fontWidth, fontHeight, null)
                }

                lineIndex++
                charIndex = 0
            }
        }
    }

    fun renderImage(graphics: Graphics, image: Image, x: Int = this.x, y: Int = this.y, width: Int = this.width, height: Int = this.height) {
        val position = translate(x, y, width, height)
        graphics.drawImage(image, position.x, position.y, width, height, null)
    }

    private fun translate(x: Int, y: Int): Vector2I {
        if(x < 0 || y < 0 || x > width || y > height) throw PositionTranslationException(x, y, 0, width, 0, height)
        return Vector2I(this.x + x, this.y + y)
    }

    private fun translate(x: Int, y: Int, width: Int, height: Int): Vector2I {
        if(x < 0 || y < 0 || width > this.width || height > this.height) throw PositionTranslationException(x, y, 0, width, 0, height)
        return Vector2I(this.x + x, this.y + y)
    }

    override fun toString(): String = name
}