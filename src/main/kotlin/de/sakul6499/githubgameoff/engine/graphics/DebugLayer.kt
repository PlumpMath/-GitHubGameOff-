package de.sakul6499.githubgameoff.engine.graphics

import de.sakul6499.githubgameoff.engine.GameMain
import de.sakul6499.githubgameoff.engine.graphics.asset.SpriteLoader
import java.awt.Color
import java.awt.Graphics
import java.util.*

class DebugLayer : Layer("Debug", 9999) {
    override var isActive: Boolean = false

    private var lastDelta: Long = 0L
    private var lastAlpha: Long = 0L

    private val diaSize = 4
    private val diaLimit = 128
    private var fpsDia: Array<Int> = arrayOf()
    private var upsDia: Array<Int> = arrayOf()
    private var maxValue: Int = 0

    override fun update(delta: Long, alpha: Long) {
        if (fpsDia.size > diaLimit) {
            // shift if limit reached
            fpsDia = Arrays.copyOfRange(fpsDia, 1, 128)
            upsDia = Arrays.copyOfRange(upsDia, 1, 128)
        }

        // add new data
        fpsDia += GameMain.FPS
        upsDia += GameMain.UPS

        // determine and increment max value
        if (GameMain.FPS > maxValue) maxValue = GameMain.FPS
        if (GameMain.UPS > maxValue) maxValue = GameMain.UPS
        incrementMaxValueToBase()

        lastDelta = delta
        lastAlpha = alpha
    }

    private fun incrementMaxValueToBase() {
        while (maxValue % 50 != 0) maxValue++
    }

    override fun render(graphics: Graphics) {
        renderRectangle(graphics, getStartX(), getStartY(), getWidth(), getHeight())
        renderText(graphics, "DEBUG", getWidth() / 2, getHeight() / 2, centered = true)
        renderImage(graphics, SpriteLoader.createPlaceholderImage(), (getWidth() / 2) - 64 / 2, (getHeight() - (getHeight() / 4)) - 64 / 2, 64, 64)

        renderText(graphics, "UPS: ${GameMain.UPS}", fontWidth = 32, fontHeight = 32)
        renderText(graphics, "FPS: ${GameMain.FPS}", y = 32, fontWidth = 32, fontHeight = 32)

        renderText(graphics, "Delta: $lastDelta", y = 128, fontWidth = 32, fontHeight = 32)
        renderText(graphics, "Alpha: $lastAlpha", y = 150, fontWidth = 32, fontHeight = 32)

        for (i in 0 until fpsDia.size) {
            val fpsEntry = fpsDia[i]
            val upsEntry = upsDia[i]

            renderRectangle(graphics, 32 + i * diaSize, getHeight() - fpsEntry, diaSize, fpsEntry, Color.GREEN)
            renderRectangle(graphics, getWidth() - 32 - i * diaSize, getHeight() - upsEntry, diaSize, upsEntry, Color.ORANGE)

            for (a in 0 until maxValue) {
                if (a % 50 == 0) {
                    renderRectangle(graphics, 32, getHeight() - a, diaLimit * diaSize, 1, Color.RED)
                    renderRectangle(graphics, getWidth() - 32 - diaLimit * diaSize, getHeight() - a, (diaLimit + 1) * diaSize, 1, Color.RED)
                }
            }
        }
    }
}