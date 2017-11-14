package de.sakul6499.githubgameoff.game.entity

import com.studiohartman.jamepad.ControllerButton
import de.sakul6499.githubgameoff.game.GameMain
import de.sakul6499.githubgameoff.game.Renderable
import de.sakul6499.githubgameoff.game.asset.SpriteFont
import de.sakul6499.githubgameoff.game.gui.Text
import de.sakul6499.githubgameoff.game.input.ControllerHandler
import de.sakul6499.githubgameoff.game.input.KeyboardHandler
import de.sakul6499.githubgameoff.game.maths.Vector2F
import java.awt.Color
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.util.concurrent.CopyOnWriteArrayList

class Player : Entity(Vector2F((GameMain.gameConfig.width / 2).toFloat(), (GameMain.gameConfig.height / 2).toFloat())), Renderable {
    val width: Int = 50
    val height: Int = 50

    val text: Text = Text(position.x.toInt(), position.y.toInt(), "Player", SpriteFont.FontColor.WHITE, SpriteFont.FontType.NORMAL, width, height)

    internal val bullets: CopyOnWriteArrayList<Bullet> = CopyOnWriteArrayList()

    override fun update(deltaTime: Double) {
        // Movement
        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_W) || ControllerHandler.IsButtonPressed(ControllerButton.DPAD_UP)) {
            movement.y -= speedMultiplier
            if (movement.y < -maxSpeed) movement.y = -maxSpeed
        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_S) || ControllerHandler.IsButtonPressed(ControllerButton.DPAD_DOWN)) {
            movement.y += speedMultiplier
            if (movement.y > maxSpeed) movement.y = maxSpeed
        } else {
            when {
                movement.y > 0.1 -> movement.y -= speedMultiplier
                movement.y < -0.1 -> movement.y += speedMultiplier
                else -> movement.y = 0F
            }
        }

        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_A) || ControllerHandler.IsButtonPressed(ControllerButton.DPAD_LEFT)) {
            movement.x -= speedMultiplier
            if (movement.x < -maxSpeed) movement.x = -maxSpeed
        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_D) || ControllerHandler.IsButtonPressed(ControllerButton.DPAD_RIGHT)) {
            movement.x += speedMultiplier
            if (movement.x > maxSpeed) movement.x = maxSpeed
        } else {
            when {
                movement.x > 0.1 -> movement.x -= speedMultiplier
                movement.x < -0.1 -> movement.x += speedMultiplier
                else -> movement.x = 0F
            }
        }

        if (!movement.isNull()) position.add(movement)

        // Bullets
        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_SPACE)) {
            bullets += Bullet(position.copy(), Bullet.BulletType.NORMAL_RED, this)
        }

        bullets.iterator().forEach { it.update(deltaTime) }

        // Set Text for ... facing ... indicator
        text.setText(getFacing().toString())
        text.x = position.x.toInt()
        text.y = position.y.toInt()
    }

    override fun render(deltaTime: Double, graphics: Graphics) {
        // Player
        graphics.color = Color.GREEN
        graphics.fillOval(position.x.toInt() - width / 2, position.y.toInt() - height / 2, width / 2, height / 2)

        // Bullet
        bullets.iterator().forEach { it.render(deltaTime, graphics) }

        // Text indicator
        text.render(deltaTime, graphics)
    }
}