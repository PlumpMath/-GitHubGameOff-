package de.sakul6499.githubgameoff.game.entity

import com.studiohartman.jamepad.ControllerAxis
import de.sakul6499.githubgameoff.engine.graphics.Layer
import de.sakul6499.githubgameoff.engine.graphics.Screen
import de.sakul6499.githubgameoff.engine.input.ControllerHandler
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import java.awt.Color
import java.awt.Graphics

object PlayerLayer : Layer("Player", 5, 0, 0, 64, 64) {
    override val isActive: Boolean = true

    private val position: Vector2F = Vector2F(Screen.GetWidth() / 2, Screen.GetHeight() / 2)
    private var movement: Vector2F = Vector2F()

//    private val text: Text = Text(position.x.toInt(), position.y.toInt(), "Player", SpriteFont.FontColor.WHITE, SpriteFont.FontType.NORMAL, 32, 32)
//    private val speedText: Text = Text(position.x.toInt(), position.y.toInt() + 32, "Speed", SpriteFont.FontColor.WHITE, SpriteFont.FontType.NORMAL, 32, 32)
//
//    private val cross = Vector2F(0F, 0F)
//    private val crossDistance = 64

//    private val bullets: CopyOnWriteArrayList<Bullet> = CopyOnWriteArrayList()

    override fun update(delta: Long, alpha: Long) {
        movement = Vector2F(ControllerHandler.GetAxisValue(ControllerAxis.LEFTX), ControllerHandler.GetAxisValue(ControllerAxis.LEFTY))

        position.add(movement)

        this.x = position.getRoundX()
        this.y = position.getRoundY()

//        // Cross
//        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_UP)) {
//            cross.y = -crossDistance.toFloat()
//        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_DOWN)) {
//            cross.y = crossDistance.toFloat()
//        } else {
//            cross.y = 0F
//        }
//
//        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_LEFT)) {
//            cross.x = -crossDistance.toFloat()
//        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_RIGHT)) {
//            cross.x = crossDistance.toFloat()
//        } else {
//            cross.x = 0F
//        }
//
//        if (!cross.isNull()) bullets += Bullet(position.copy(), cross.copy().divide(8F, 8F), Bullet.BulletType.NORMAL_RED, this)
//
//        bullets.iterator().forEach { it.update(delta, alpha) }
//
//        // Set Text for ... facing ... indicator
//        text.setText("${getFacing()}")
//        text.x = position.x.toInt()
//        text.y = position.y.toInt()
//
//        speedText.setText("$speed / $maxSpeed [$speedMultiplier * $alpha = ${speedMultiplier * alpha}]")
//        speedText.x = position.x.toInt()
//        speedText.y = position.y.toInt() + 32
    }

    override fun render(graphics: Graphics) {
        // Player
        graphics.color = Color.GREEN
        graphics.fillOval(position.x.toInt() - width / 2, position.y.toInt() - height / 2, width / 2, height / 2)

//        // Bullet
//        bullets.iterator().forEach { it.render(graphics) }

//        // Text indicator
//        text.render(graphics)
//        speedText.render(graphics)
//
//        if (!cross.isNull()) {
//            graphics.color = Color.CYAN
//            graphics.fillRect((position.getRoundX() + cross.getRoundX()) - width / 2, (position.getRoundY() + cross.getRoundY()) - height / 2, width / 2, height / 2)
//        }
    }
}