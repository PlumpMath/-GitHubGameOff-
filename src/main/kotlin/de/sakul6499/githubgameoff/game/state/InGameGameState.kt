package de.sakul6499.githubgameoff.game.state

import de.sakul6499.githubgameoff.game.GameMain
import de.sakul6499.githubgameoff.game.asset.SpriteFont
import de.sakul6499.githubgameoff.game.gui.Button
import java.awt.Color
import java.awt.Graphics

class InGameGameState : GameState {
    override val name: String = "InGame"
    private val button: Button = Button(200, 200, "Test!", true, 0, 0, 15, 15, 15, 15, SpriteFont.tileDimension / 2, SpriteFont.tileDimension / 2, SpriteFont.tileDimension / 4, SpriteFont.FontColor.BLACK, SpriteFont.FontType.BOLD)

    override fun update(deltaTime: Double) {
        button.update(deltaTime)
    }

    override fun render(deltaTime: Double, graphics: Graphics) {
        // TODO: remove
        graphics.color = Color.BLACK
        graphics.fillRect(0, 0, GameMain.gameConfig.width, GameMain.gameConfig.height)
        // ###

        button.render(deltaTime, graphics)
    }
}