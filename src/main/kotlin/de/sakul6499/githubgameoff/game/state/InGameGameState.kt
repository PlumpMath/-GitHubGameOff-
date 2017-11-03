package de.sakul6499.githubgameoff.game.state

import de.sakul6499.githubgameoff.game.GameMain
import de.sakul6499.githubgameoff.game.asset.SpriteFont
import de.sakul6499.githubgameoff.game.asset.SpriteLoader
import de.sakul6499.githubgameoff.game.gui.Button
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
import java.awt.image.DataBufferInt

class InGameGameState : GameState {
    override val name: String = "InGame"
    private val button: Button = Button(200.0, 200.0, "Test!")

    //    val image = ImageIO.read(this::class.java.getResource("/font/font_NORMAL.png"))
    val image: BufferedImage

    init {
        button.makeActive()

        val from = Color.decode("#000000")
        val to = Color.WHITE

        SpriteLoader.registerSprite("test", this::class.java.getResource("/font/font_NORMAL.png"))
        SpriteLoader.defineTile("test", "test", 128, 0, 128, 128)
//        val lookup = LookupOp(ColorMapper(from, to), null)
//        image = lookup.filter(SpriteLoader.getTile("test"), null)
        val tile = SpriteLoader.getTile("test")
        image = BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB)

        val oldPixels = (tile.raster.dataBuffer as DataBufferByte).data
        val newPixels = (image.raster.dataBuffer as DataBufferInt).data

        for (x in 0 until image.width) {
//            var result = ""
            for (y in 0 until image.height) {
//                result += "${image.getRGB(x, y)} "

                if (oldPixels[y * image.width + x] == 0.toByte()) {
                    newPixels[y * image.width + x] = SpriteFont.calcRGBA(100, 100, 100, 100)
                    println("SET")
                } else {
                    newPixels[y * image.width + x] = 0
                    println("NULL")
                }
            }
//            println("#$x $result")
        }
        SpriteFont.map[name] = image
//        image = SpriteFont.getChar('!', SpriteFont.FontType.NORMAL, 255, 255, 255, 100)
    }

    override fun update(deltaTime: Double) {
//        if(button.active) button.makeInative() else button.makeActive()

    }

    override fun render(deltaTime: Double, graphics: Graphics) {
        graphics.color = Color.WHITE
        graphics.fillRect(0, 0, GameMain.gameConfig.width, GameMain.gameConfig.height)

//        button.render(deltaTime, graphics)

        graphics.drawImage(image, 0, 0, 512, 512, null)


        //            DebugFrame.launch(ImageIO.read(this::class.java.getResource("/font/font_NORMAL.png")))

//            SpriteLoader.registerSprite("test", this::class.java.getResource("/font/font_NORMAL.png"))
//            SpriteLoader.defineTile("test", "test", 128, 128, 128, 128)
//            val image = SpriteLoader.getTile("test")
//            DebugFrame.launch(image)
//            for(x in 0 until image.width) {
//                var result = ""
//                for(y in 0 until image.height) {
//                   result += "${image.getRGB(x, y)} "
//                }
//                println("#$x -> $result")
//            }
    }
}