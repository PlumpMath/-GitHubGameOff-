package de.sakul6499.githubgameoff.game

import de.sakul6499.githubgameoff.engine.graphics.RenderOnceLayer
import de.sakul6499.githubgameoff.engine.graphics.asset.Image
import de.sakul6499.githubgameoff.engine.graphics.asset.Images
import de.sakul6499.githubgameoff.engine.graphics.asset.StagedImage
import de.sakul6499.githubgameoff.engine.maths.BoxVector
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.engine.maths.Vector2I
import de.sakul6499.githubgameoff.game.entity.renderBox
import java.awt.Color
import java.awt.Graphics
import java.util.*
import javax.imageio.ImageIO

object BackgroundLayer : RenderOnceLayer("Background", 0, v0 = Vector2I(0, 0)) {
    override val isActive: Boolean = true

    val amountTilesX = 21
    val amountTilesY = 9

    val tileSizeX = getWidth() / amountTilesX
    val tileSizeY = getHeight() / amountTilesY

//    val tileSize = 64
//    val amountTilesX = getWidth() / tileSize
//    val amountTilesY = getHeight() / tileSize

    // TODO: left

    fun getPlayableArea(): BoxVector<Vector2F> = BoxVector(Vector2F(getStartX() + tileSizeX, getStartY() + tileSizeY), Vector2F(getEndX() - tileSizeX, getEndY() - tileSizeY))

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
        for (w in 0 until amountTilesX) {
            for (h in 0 until amountTilesY) {
                val w0 = w == 0
                val w1 = w == amountTilesX - 1
                val h0 = h == 0
                val h1 = h == amountTilesY - 1

                if (w0 && h0) {
                    renderImage(graphics, Images.getImage("wall_edge", 0), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                    graphics.renderBox(w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                    println("${w * tileSizeX} ${h * tileSizeY}")
                } else if (w0 && h1) {
                    renderImage(graphics, Images.getImage("wall_edge", 3), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (w1 && h0) {
                    renderImage(graphics, Images.getImage("wall_edge", 1), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (w1 && h1) {
                    renderImage(graphics, Images.getImage("wall_edge", 2), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (w0) {
                    renderImage(graphics, Images.getImage("wall_mid", 3), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (w1) {
                    renderImage(graphics, Images.getImage("wall_mid", 1), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (h0) {
                    renderImage(graphics, Images.getImage("wall_mid", 0), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (h1) {
                    renderImage(graphics, Images.getImage("wall_mid", 2), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (w == tileSizeX) {
                    renderImage(graphics, Images.getImage("wall_mid", 3), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (w == (amountTilesX - tileSizeX) / tileSizeX - 1) {
                    renderImage(graphics, Images.getImage("wall_mid", 1), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (h == tileSizeY) {
                    renderImage(graphics, Images.getImage("wall_mid", 0), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else if (h == (amountTilesY - tileSizeY) / tileSizeY - 1) {
                    renderImage(graphics, Images.getImage("wall_mid", 2), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                } else {
                    renderImage(graphics, Images.getImage("floor", Random().nextInt(4)), w * tileSizeX, h * tileSizeY, tileSizeX, tileSizeY)
                }
            }
        }

        renderImage(graphics, Images.getImage("floor_mid"), getWidth() / 2 - (tileSizeX * 8) / 2, getHeight() / 2 - (46 * 8) / 2, (tileSizeY * 8), (46 * 8))

        val b = getPlayableArea()
        println(b)
        println(getEndX())
        println(getEndY())
//        graphics.color = Color.RED
//        graphics.fillRect(b.v0.x.toInt(), b.v0.y.toInt(), b.v1.x.toInt(), b.v1.y.toInt())
        graphics.renderBox(b.v0.toVector2I(), b.v1.toVector2I(), Color.RED)
    }
}