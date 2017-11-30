package de.sakul6499.githubgameoff.engine.graphics

import de.sakul6499.githubgameoff.engine.Engine
import de.sakul6499.githubgameoff.engine.graphics.asset.SpriteFont
import de.sakul6499.githubgameoff.engine.maths.BoxVector
import de.sakul6499.githubgameoff.engine.maths.Vector
import de.sakul6499.githubgameoff.engine.maths.Vector2I
import java.awt.Color
import java.awt.Graphics
import java.awt.Image

abstract class BasicLayer(val name: String, internal var order: Int, internal var v0: Vector2I = Vector2I(0, 0), internal var v1: Vector2I = Vector2I(Screen.GetWidth(), Screen.GetHeight())) {
    abstract val isActive: Boolean

    fun getBox(): BoxVector<Vector2I> = BoxVector(v0, v1)
    fun getWidth(): Int = v1.x - v0.x
    fun getHeight(): Int = v1.y - v0.y
    fun getStartX(): Int = v0.x
    fun getStartY(): Int = Screen.offset + v0.y
    fun getEndX(): Int = v1.x
    fun getEndY(): Int = v1.y

    fun renderRectangle(graphics: Graphics, x: Int = getStartX(), y: Int = getStartY(), width: Int = getWidth(), height: Int = getHeight(), color: Color = Color.BLACK) {
        val position = translate(x, y, width, height)
        Engine.renderRectangle(graphics, position.x, position.y, width, height, color)
    }

    fun renderText(graphics: Graphics, text: String, x: Int = getStartX(), y: Int = getStartY(), centered: Boolean = false, fontColor: SpriteFont.FontColor = SpriteFont.FontColor.WHITE, fontType: SpriteFont.FontType = SpriteFont.FontType.NORMAL, fontWidth: Int = SpriteFont.tileDimension, fontHeight: Int = SpriteFont.tileDimension, fontVerticalSpacing: Int = fontWidth / 2) {
        val position = translate(x, y)
        Engine.renderText(graphics, text, position.x, position.y, centered, fontColor, fontType, fontWidth, fontHeight, fontVerticalSpacing)
    }

    fun renderImage(graphics: Graphics, image: Image, x: Int = getStartX(), y: Int = getStartY(), width: Int = getWidth(), height: Int = getHeight()) {
        val position = translate(x, y, width, height)
        Engine.renderImage(graphics, image, position.x, position.y, width, height)
    }

    fun renderBox(graphics: Graphics, x: Int = getStartX(), y: Int = getStartY(), width: Int = getWidth(), height: Int = getHeight(), color: Color = Color.RED) {
        val position = translate(x, y, width, height)
        Engine.renderBox(graphics, position.x, position.y, width, height)
    }

    internal fun translate(x: Int, y: Int, width: Int = getWidth(), height: Int = getHeight()): Vector2I {
        if (isRangeInvalid(x, y, width, height)) throw PositionTranslationException(BoxVector<Vector<Int>>(Vector2I(x, y), Vector2I(width, height)), getBox(), this)
        return Vector2I(getStartX() + x, getStartY() + y)
    }

    fun isRangeValid(x: Int, y: Int, width: Int = getWidth(), height: Int = getHeight()): Boolean = !isRangeInvalid(x, y, width, height)
    fun isRangeInvalid(x: Int, y: Int, width: Int = getWidth(), height: Int = getHeight()): Boolean = (x < 0 || y < 0 || x > getWidth() || y > getHeight() || x + width < 0 || y + height < 0 || x + width > getWidth() || y + height > getHeight())

    override fun toString(): String = "BasicLayer: $name [active: $isActive]"
}