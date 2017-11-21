package de.sakul6499.githubgameoff.engine.asset

import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO

object SpriteLoader {
    private val Sprites: MutableMap<String, BufferedImage> = mutableMapOf()
    private val Tiles: MutableMap<String, BufferedImage> = mutableMapOf()

    fun registerSprite(spriteName: String, file: File) = registerSprite(spriteName, file.toURI().toURL())
    fun registerSprite(spriteName: String, url: URL) {
        println("Attempting to register sprite $spriteName!")
        val bufferedImage: BufferedImage = try {
            ImageIO.read(url)

        } catch (ex: IOException) {
            println("Warning: Failed loading asset '$spriteName' [$url]!")
            createPlaceholderImage()
        }

        Sprites[spriteName] = bufferedImage
    }

    fun defineTile(tileName: String, spriteName: String, tileX: Int, tileY: Int, sizeX: Int, sizeY: Int) {
        println("Attempting to define tile $tileName on $spriteName!")
        Tiles[tileName] = Sprites[spriteName]!!.getSubimage(tileX, tileY, sizeX, sizeY)
    }

    fun defineTileArray(tileName: String, spriteName: String, tileX: Int, tileY: Int, sizeX: Int, sizeY: Int, times: Int) {
        for (i in 0 until times) defineTile("${tileName}_$i", spriteName, tileX + sizeX * i, tileY, sizeX, sizeY)
    }

    fun getTile(tileName: String): BufferedImage = Tiles[tileName] ?: createPlaceholderImage()

    fun getTileArray(tileName: String): List<BufferedImage> = Tiles.filterKeys { it.startsWith("${tileName}_") }.map { it.value }

    fun createPlaceholderImage(): BufferedImage {
        val bufferedImage = BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB)
        val graphics2D = bufferedImage.graphics as Graphics2D
        graphics2D.color = Color.MAGENTA
        graphics2D.fillRect(0, 0, bufferedImage.width / 2, bufferedImage.height / 2)
        graphics2D.fillRect(bufferedImage.width / 2, bufferedImage.height / 2, bufferedImage.width, bufferedImage.height)
        graphics2D.color = Color.PINK
        graphics2D.fillRect(0, bufferedImage.height / 2, bufferedImage.width / 2, bufferedImage.height)
        graphics2D.fillRect(bufferedImage.width / 2, 0, bufferedImage.width, bufferedImage.height / 2)
        return bufferedImage
    }
}