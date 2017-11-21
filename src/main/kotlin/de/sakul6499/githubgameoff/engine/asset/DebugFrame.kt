package de.sakul6499.githubgameoff.engine.asset

import java.awt.Dimension
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

object DebugFrame {
    fun launch(image: BufferedImage){
        val frame: JFrame = JFrame("Debug Frame")
        frame.size = Dimension(500, 500)
        frame.layout = null
        frame.isResizable = true
        frame.setLocationRelativeTo(null)
        frame.isLocationByPlatform = false
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame.add(JLabel(ImageIcon(image)))

        frame.isVisible = true
    }
}