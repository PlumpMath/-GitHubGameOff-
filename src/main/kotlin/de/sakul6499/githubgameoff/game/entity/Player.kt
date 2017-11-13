package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.game.GameMain
import de.sakul6499.githubgameoff.game.asset.SpriteFont
import de.sakul6499.githubgameoff.game.gui.Renderable
import de.sakul6499.githubgameoff.game.gui.Text
import java.awt.Color
import java.awt.Graphics

class Player : Entity((GameMain.gameConfig.width / 2).toFloat(), (GameMain.gameConfig.height / 2).toFloat()), Renderable {
    val width: Int = 50
    val height: Int = 50

    val text: Text = Text(position.x.toInt(), position.y.toInt(), "Player", SpriteFont.FontColor.WHITE, SpriteFont.FontType.NORMAL, width, height)

    override fun render(deltaTime: Double, graphics: Graphics) {
        graphics.color = Color.GREEN
        graphics.fillOval(position.x.toInt() - width / 2, position.y.toInt() - height / 2, width / 2, height / 2)

        text.render(deltaTime, graphics)
    }

    override fun postUpdate(): Boolean {
        val facing = getFacing()

        text.setText(facing.toString())
        text.x = position.x.toInt()
        text.y = position.y.toInt()

        return super.postUpdate()
    }
}