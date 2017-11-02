package de.sakul6499.githubgameoff.gui

import java.awt.Graphics

class Button(x: Double, y: Double, text: String? = null) : GUIBasicTextElement(x, y, 0.0, 0.0, text) {
    override fun renderElement(deltaTime: Double, graphics: Graphics) {
        renderText(graphics, 100.0, 100.0, 50.0, 50.0, text!!)

//        val affineTransform = AffineTransform()
//        val frc = FontRenderContext(affineTransform.0, true, true)
//        val width = font.getStringBounds(text, frc).width.toInt()
//        val height = font.getStringBounds(text, frc).height.toInt()
//
////        val metrics = graphics.getFontMetrics(font)
////        val height = metrics.height
////        val width = metrics.stringWidth(text)
//
////        val textLayout = TextLayout(text, font, FontRenderContext(null, false, false))
////        this.sizeX = textLayout.bounds.width
////        this.sizeY = textLayout.bounds.height
////        val buttonX = (this.x - this.sizeX / 2).toInt()
////        val buttonY = (this.y - this.sizeY / 2).toInt()
//
////        println(sizeX)
////        println(sizeY)
////        println(buttonX)
////        println(buttonY)
//
//        println(height)
//        println(width)
////        println(textLayout.bounds.x.toInt())
////        println(textLayout.bounds.y.toInt())
////        println(textLayout.bounds.width.toInt())
////        println(textLayout.bounds.height.toInt())
//        println()
//
//        graphics.color = Color.WHITE
////        graphics.fillRect(buttonX, buttonY, sizeX.toInt(), sizeY.toInt())
//        graphics.fillRect(x.toInt(), y.toInt(), width+ 2, height + 2)
//
////        graphics.font = Font("Tahoma", Font.PLAIN, 28)
//        graphics.font = font
//        graphics.color = Color.MAGENTA
//        graphics.drawString("This is a Test", x.toInt(), y.toInt())
    }
}