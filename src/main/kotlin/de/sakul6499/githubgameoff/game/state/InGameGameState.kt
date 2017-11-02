package de.sakul6499.githubgameoff.game.state

import de.sakul6499.githubgameoff.game.GameMain
import de.sakul6499.githubgameoff.game.gui.Button
import java.awt.Color
import java.awt.Graphics

class InGameGameState : GameState {
    override val name: String = "InGame"
    private val button: Button = Button(200.0, 200.0, "Test!")

    init {
        button.makeActive()
    }

    override fun update(deltaTime: Double) {
//        if(button.active) button.makeInative() else button.makeActive()

    }

    override fun render(deltaTime: Double, graphics: Graphics) {
        graphics.color = Color.BLACK
        graphics.fillRect(0, 0, GameMain.gameConfig.width, GameMain.gameConfig.height)

        button.render(deltaTime, graphics)
    }
}