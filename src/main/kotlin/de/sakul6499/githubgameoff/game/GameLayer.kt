package de.sakul6499.githubgameoff.game

import de.sakul6499.githubgameoff.engine.Layer
import de.sakul6499.githubgameoff.engine.asset.SpriteLoader
import de.sakul6499.githubgameoff.game.entity.Player
import java.awt.Color
import java.awt.Graphics

object GameLayer: Layer("Game", 0, y = 12) {
    override var isActive: Boolean = true

    val player: Player = Player()

    init {
        println("Initializing GameLayer ...")
        SpriteLoader.registerSprite("basic_tiles", this.javaClass.getResource("/basic_tiles.png"))
        SpriteLoader.defineTileArray("grass", "basic_tiles", 0, 0, 16, 16, 3)
        SpriteLoader.defineTileArray("stone", "basic_tiles", 0, 16, 16, 16, 2)
        SpriteLoader.defineTile("rock", "basic_tiles", 0, 32, 16, 16)
        SpriteLoader.defineTile("wall", "basic_tiles", 0, 48, 16, 16)

        SpriteLoader.registerSprite("WALL", this.javaClass.getResource("/tiles/Wall.png"))
        SpriteLoader.defineTile("WALL", "WALL", 0, 0, 256, 256)

        SpriteLoader.registerSprite("Background", this.javaClass.getResource("/tiles/Background.png"))
        SpriteLoader.defineTile("Background", "Background", 0, 0, 2484, 1200)
    }

    override fun update(delta: Long, alpha: Long) {
        player.update(delta, alpha)
    }

    override fun render(graphics: Graphics) {
        renderImage(graphics, SpriteLoader.getTile("Background"))
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