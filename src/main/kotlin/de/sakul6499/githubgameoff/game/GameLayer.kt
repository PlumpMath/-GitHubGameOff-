package de.sakul6499.githubgameoff.game

import de.sakul6499.githubgameoff.engine.graphics.Layer
import de.sakul6499.githubgameoff.game.entity.Player
import java.awt.Graphics

object GameLayer : Layer("Game", 100) {
    override var isActive: Boolean = true

    val player: Player = Player()

    init {
        println("Initializing $this ...")
    }

    override fun update(delta: Long, alpha: Long) {
        player.update(delta, alpha)
    }

    override fun render(graphics: Graphics) {
//        // TODO render background once
//        renderRectangle(graphics, color = Color.BLACK)
//        graphics.color = Color.BLACK
//        graphics.fillRect(0, 0, width, height)



//        for(i in 0..height / 64) {
//            renderImage(graphics, image, 0, i*64, 64, 64)
//            renderImage(graphics, image, width - 64, i*64,  64, 64)
//        }

//        renderImage(graphics, SpriteLoader.getTile("Background"))
//        val tileSize = width / 16
//
//        for(y in 0..height / tileSize) {
//            for(x in 0..width / tileSize) {
//                if(x == 0 || y == 0 || x == width / tileSize || y == height / tileSize) {
//                    val t = 128
//                    renderImage(graphics, SpriteLoader.getTile("WALL"), 128 * x, 128 * y, 128, 128)
//                } else {
//                    val ran = Math.random()
//                    if(ran < 0.25) {
//                        renderImage(graphics, SpriteLoader.getTile("wall"), tileSize * x, tileSize * y, tileSize, tileSize)
//                    } else if(ran < 0.5) {
//                        renderImage(graphics, SpriteLoader.getTile("rock"), tileSize * x, tileSize * y, tileSize, tileSize)
//                    } else if(ran < 0.75) {
//                        renderImage(graphics, SpriteLoader.getTile("stone_0"), tileSize * x, tileSize * y, tileSize, tileSize)
//                    } else {
//                        renderImage(graphics, SpriteLoader.getTile("stone_1"), tileSize * x, tileSize * y, tileSize, tileSize)
//                    }
//                }
//            }
//        }

//        var xi = 0
//        var yi = 0
//        val size = 128
//
//        SpriteLoader.getTileArray("grass").forEach {
//            renderImage(graphics, it, 32 + size * xi, 32 + size * yi, size, size)
//            xi++
//        }
//        yi++
//
//        xi = 0
//        SpriteLoader.getTileArray("stone").forEach {
//            renderImage(graphics, it, 32 + size * xi, 32 + size * yi, size, size)
//            xi++
//        }
//        yi++
//
//        renderImage(graphics, SpriteLoader.getTile("rock"), 32, 32 + size * yi, 128, 128)
//        yi++
//
//        renderImage(graphics, SpriteLoader.getTile("wall"), 32, 32 + size * yi, 128, 128)
//        yi++
//
//        renderImage(graphics, SpriteLoader.getTile("stone_0"), 32, 32 + size * yi, 128, 128)
//        yi++
//        renderImage(graphics, SpriteLoader.getTile("stone_1"), 32, 32 + size * yi, 128, 128)
//        yi++

        player.render(graphics)
    }
}