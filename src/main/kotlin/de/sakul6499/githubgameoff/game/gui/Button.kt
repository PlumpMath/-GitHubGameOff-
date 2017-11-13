package de.sakul6499.githubgameoff.game.gui

import de.sakul6499.githubgameoff.game.asset.SpriteFont
import de.sakul6499.githubgameoff.game.input.MouseHandler
import de.sakul6499.githubgameoff.game.maths.Maths
import java.awt.Color
import java.awt.Graphics

class Button(x: Int, y: Int, text: String, var autoSize: Boolean = false, var width: Int = 0, var height: Int = 0, var paddingUp: Int = 0, var paddingDown: Int = 0, var paddingLeft: Int = 0, var paddingRight: Int = 0, fontWidth: Int = width, fontHeight: Int = height, fontVerticalSpacing: Int = width / 2, fontColor: SpriteFont.FontColor = SpriteFont.FontColor.BLACK, fontType: SpriteFont.FontType = SpriteFont.FontType.BOLD) : Text(x, y, text, fontColor, fontType, fontWidth, fontHeight, fontVerticalSpacing), Updateable, Renderable {

    private var hover: Boolean = false

    init {
        setText(text)
    }

    override fun update(deltaTime: Double) {
        hover = Maths.isInAABB(MouseHandler.MousePosition.x, MouseHandler.MousePosition.y, getTotalMinX(), getTotalMinY(), getTotalMaxX(), getTotalMaxY())
    }

    override fun render(deltaTime: Double, graphics: Graphics) {
        graphics.color = if (hover) Color.GRAY else Color.WHITE
        graphics.fillRect(x, y, width, height)

        if (paddingUp > 0) {
            graphics.color = Color.ORANGE
            graphics.fillRect(getTotalMinX(), getTotalMinY(), getTotalWidth(), paddingUp)
        }

        if (paddingDown > 0) {
            graphics.color = Color.GREEN
            graphics.fillRect(getTotalMinX(), y + height, getTotalWidth(), paddingDown)
        }

        if (paddingLeft > 0) {
            graphics.color = Color.RED
            graphics.fillRect(x - paddingLeft, getTotalMinY(), paddingLeft, getTotalHeight())
        }

        if (paddingRight > 0) {
            graphics.color = Color.BLUE
            graphics.fillRect(x + width, getTotalMinY(), paddingRight, getTotalHeight())
        }

        super.render(deltaTime, graphics)
    }

    override fun setText(text: String): Int {
        val length = super.setText(text)
        if (autoSize) {
            width = length
            height = fontHeight
        }
        return length
    }

    fun getTotalWidth(): Int = width + paddingLeft + paddingRight
    fun getTotalHeight(): Int = height + paddingUp + paddingDown
    fun getTotalMinX(): Int = x - paddingLeft
    fun getTotalMaxX(): Int = x + width + paddingRight
    fun getTotalMinY(): Int = y - paddingUp
    fun getTotalMaxY(): Int = y + height + paddingDown
}