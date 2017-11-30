package de.sakul6499.githubgameoff.game

import de.sakul6499.githubgameoff.engine.graphics.RenderOnceLayer
import de.sakul6499.githubgameoff.engine.graphics.asset.Image
import de.sakul6499.githubgameoff.engine.graphics.asset.Images
import de.sakul6499.githubgameoff.engine.graphics.asset.StagedImage
import de.sakul6499.githubgameoff.engine.maths.Vector2I
import java.awt.Graphics
import java.util.*
import javax.imageio.ImageIO

object BackgroundLayer : RenderOnceLayer("Background", 0, v0 = Vector2I(0, 0)) {
    override val isActive: Boolean = true

    init {
        println("Initializing $this ...")

        Images.registerImage("wall_edge", StagedImage.CreateRotationBasedStagedImage(ImageIO.read(this.javaClass.getResource("/tiles/wall_edge.png")), 4, Math.PI / 2))
        Images.registerImage("wall_mid", StagedImage.CreateRotationBasedStagedImage(ImageIO.read(this.javaClass.getResource("/tiles/wall_mid.png")), 4, Math.PI / 2))
        Images.registerImage("floor_wall", StagedImage(arrayOf(
                Image(ImageIO.read(this.javaClass.getResource("/tiles/floor_wall_0.png"))),
                Image(ImageIO.read(this.javaClass.getResource("/tiles/floor_wall_1.png"))),
                Image(ImageIO.read(this.javaClass.getResource("/tiles/floor_wall_2.png")))
        )))
        Images.registerImage("floor", StagedImage(arrayOf(
                Image(ImageIO.read(this.javaClass.getResource("/tiles/floor_0.png"))),
                Image(ImageIO.read(this.javaClass.getResource("/tiles/floor_1.png"))),
                Image(ImageIO.read(this.javaClass.getResource("/tiles/floor_2.png"))),
                Image(ImageIO.read(this.javaClass.getResource("/tiles/floor_3.png")))
        )))
        Images.registerImage("floor_mid", Image(ImageIO.read(this.javaClass.getResource("/tiles/floor_mid.png"))))
    }

    override fun render(graphics: Graphics) {
        val tw = getWidth() / 64
        val th = getHeight() / 64
        for (w in 0 until tw) {
            for (h in 0 until th) {
                val w0 = w == 0
                val w1 = w == tw - 1
                val h0 = h == 0
                val h1 = h == th - 1

                if (w0 && h0) {
                    renderImage(graphics, Images.getImage("wall_edge", 0), w * 64, h * 64, 64, 64)
                } else if (w0 && h1) {
                    renderImage(graphics, Images.getImage("wall_edge", 3), w * 64, h * 64, 64, 64)
                } else if (w1 && h0) {
                    renderImage(graphics, Images.getImage("wall_edge", 1), w * 64, h * 64, 64, 64)
                } else if (w1 && h1) {
                    renderImage(graphics, Images.getImage("wall_edge", 2), w * 64, h * 64, 64, 64)
                } else if (w0) {
                    renderImage(graphics, Images.getImage("wall_mid", 3), w * 64, h * 64, 64, 64)
                } else if (w1) {
                    renderImage(graphics, Images.getImage("wall_mid", 1), w * 64, h * 64, 64, 64)
                } else if (h0) {
                    renderImage(graphics, Images.getImage("wall_mid", 0), w * 64, h * 64, 64, 64)
                } else if (h1) {
                    renderImage(graphics, Images.getImage("wall_mid", 2), w * 64, h * 64, 64, 64)
                } else if (w == 64) {
                    renderImage(graphics, Images.getImage("wall_mid", 3), w * 64, h * 64, 64, 64)
                } else if (w == (tw - 64) / 64 - 1) {
                    renderImage(graphics, Images.getImage("wall_mid", 1), w * 64, h * 64, 64, 64)
                } else if (h == 64) {
                    renderImage(graphics, Images.getImage("wall_mid", 0), w * 64, h * 64, 64, 64)
                } else if (h == (th - 64) / 64 - 1) {
                    renderImage(graphics, Images.getImage("wall_mid", 2), w * 64, h * 64, 64, 64)
                } else {
                    renderImage(graphics, Images.getImage("floor", Random().nextInt(4)), w * 64, h * 64, 64, 64)
                }
            }
        }

        renderImage(graphics, Images.getImage("floor_mid"), getWidth() / 2 - (64 * 8) / 2, getHeight() / 2 - (46 * 8) / 2, (64 * 8), (46 * 8))
    }
}