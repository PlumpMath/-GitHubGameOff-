package de.sakul6499.githubgameoff.game.entity

import com.studiohartman.jamepad.ControllerButton
import de.sakul6499.githubgameoff.engine.GameMain
import de.sakul6499.githubgameoff.engine.Renderable
import de.sakul6499.githubgameoff.engine.asset.SpriteFont
import de.sakul6499.githubgameoff.engine.entity.Entity
import de.sakul6499.githubgameoff.engine.gui.Text
import de.sakul6499.githubgameoff.engine.input.ControllerHandler
import de.sakul6499.githubgameoff.engine.input.KeyboardHandler
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import java.awt.Color
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.util.concurrent.CopyOnWriteArrayList

class Player : Entity(Vector2F((GameMain.gameConfig.width / 2).toFloat(), (GameMain.gameConfig.height / 2).toFloat()), speedMultiplier = 0.1F), Renderable {
    val width: Int = 50
    val height: Int = 50

    val text: Text = Text(position.x.toInt(), position.y.toInt(), "Player", SpriteFont.FontColor.WHITE, SpriteFont.FontType.NORMAL, 32, 32)
    val speedText: Text = Text(position.x.toInt(), position.y.toInt() + 32, "Speed", SpriteFont.FontColor.WHITE, SpriteFont.FontType.NORMAL, 32, 32)

    val cross = Vector2F(0F, 0F)
    val crossDistance = 64

    internal val bullets: CopyOnWriteArrayList<Bullet> = CopyOnWriteArrayList()

    override fun update(delta: Long, alpha: Long) {
        // Movement
        val speed = if (alpha > 0) speedMultiplier * alpha else speedMultiplier
        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_W) || ControllerHandler.IsButtonPressed(ControllerButton.DPAD_UP)) {
            println("Before: ${movement.y}")
            movement.y -= speed
            println("After: ${movement.y}")
            if (movement.y < -maxSpeed) movement.y = -maxSpeed
            println("End: ${movement.y}")
            println()
        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_S) || ControllerHandler.IsButtonPressed(ControllerButton.DPAD_DOWN)) {
            movement.y += speed
            if (movement.y > maxSpeed) movement.y = maxSpeed
        } else {
            when {
                movement.y > 0.05 -> movement.y -= speed
                movement.y < -0.05 -> movement.y += speed
                else -> movement.y = 0F
            }
        }

        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_A) || ControllerHandler.IsButtonPressed(ControllerButton.DPAD_LEFT)) {
            movement.x -= speed
            if (movement.x < -maxSpeed) movement.x = -maxSpeed
        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_D) || ControllerHandler.IsButtonPressed(ControllerButton.DPAD_RIGHT)) {
            movement.x += speed
            if (movement.x > maxSpeed) movement.x = maxSpeed
        } else {
            when {
                movement.x > 0.05 -> movement.x -= speed
                movement.x < -0.05 -> movement.x += speed
                else -> movement.x = 0F
            }
        }

        if (!movement.isNull()) position.add(movement)

        // Cross
        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_UP)) {
            cross.y = -crossDistance.toFloat()
        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_DOWN)) {
            cross.y = crossDistance.toFloat()
        } else {
            cross.y = 0F
        }

        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_LEFT)) {
            cross.x = -crossDistance.toFloat()
        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_RIGHT)) {
            cross.x = crossDistance.toFloat()
        } else {
            cross.x = 0F
        }

        if (!cross.isNull()) bullets += Bullet(position.copy(), cross.copy().divide(8F, 8F), Bullet.BulletType.NORMAL_RED, this)

        // Bullets
//        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_SPACE)) {
//
//        }

        bullets.iterator().forEach { it.update(delta, alpha) }

        // Set Text for ... facing ... indicator
        text.setText("${getFacing()}")
        text.x = position.x.toInt()
        text.y = position.y.toInt()

        speedText.setText("$speed / $maxSpeed [$speedMultiplier * $alpha = ${speedMultiplier * alpha}]")
        speedText.x = position.x.toInt()
        speedText.y = position.y.toInt() + 32
    }

    override fun render(graphics: Graphics) {
        // Player
        graphics.color = Color.GREEN
        graphics.fillOval(position.x.toInt() - width / 2, position.y.toInt() - height / 2, width / 2, height / 2)

        // Bullet
        bullets.iterator().forEach { it.render(graphics) }

        // Text indicator
        text.render(graphics)
        speedText.render(graphics)

        if (!cross.isNull()) {
            graphics.color = Color.CYAN
            graphics.fillRect((position.getRoundX() + cross.getRoundX()) - width / 2, (position.getRoundY() + cross.getRoundY()) - height / 2, width / 2, height / 2)
        }
    }
}