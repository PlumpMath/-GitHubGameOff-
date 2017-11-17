package de.sakul6499.githubgameoff.engine.state

import de.sakul6499.githubgameoff.engine.GameMain
import de.sakul6499.githubgameoff.engine.asset.SpriteFont
import de.sakul6499.githubgameoff.game.entity.Player
import de.sakul6499.githubgameoff.engine.gui.Button
import java.awt.Color
import java.awt.Graphics

// TODO: implement EventSystem **into** GameState (each gamestate shell have it's own event system)
class InGameGameState : GameState {
    override val name: String = "InGame"
    private val button: Button = Button(200, 200, "Test!", true, 0, 0, 15, 15, 15, 15, SpriteFont.tileDimension / 2, SpriteFont.tileDimension / 2, SpriteFont.tileDimension / 4, SpriteFont.FontColor.BLACK, SpriteFont.FontType.BOLD)

    val player: Player = Player()

    init {
        println("INIT")
//        EventManager.instance.registerEvent(object: UpdateableEvent("Test") {
//            override fun shellTrigger(): Boolean = KeyboardHandler.IsKeyPressed(KeyEvent.VK_W) || MouseHandler.MouseButtonLeftPressed
//
//            override fun onEvent(): Boolean {
//                println("TRIGGER")
//
//                return true
//            }
//        })
    }

    override fun update(delta: Long, alpha: Long) {
        button.update(delta, alpha)
        player.update(delta, alpha)
    }

    override fun render(graphics: Graphics) {
        // TODO: remove
        graphics.color = Color.BLACK
        graphics.fillRect(0, 0, GameMain.gameConfig.width, GameMain.gameConfig.height)
        // ###

        button.render(graphics)

        player.render(graphics)
    }
}