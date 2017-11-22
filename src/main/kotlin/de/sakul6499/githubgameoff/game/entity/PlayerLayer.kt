package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.asset.SpriteFont
import de.sakul6499.githubgameoff.engine.graphics.Layer
import de.sakul6499.githubgameoff.engine.graphics.Screen
import de.sakul6499.githubgameoff.engine.input.Input
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.game.BackgroundLayer
import java.awt.Color
import java.awt.Graphics

object PlayerLayer : Layer("Player", 5, BackgroundLayer.getValidRange().first.x, BackgroundLayer.getValidRange().first.y, BackgroundLayer.getValidRange().second.x, BackgroundLayer.getValidRange().second.y) {
    override val isActive: Boolean = true

    internal val position: Vector2F = Vector2F(Screen.GetWidth() / 2, Screen.GetHeight() / 2)
    //    private var movement: Vector2F = Vector2F()
    private var cross: Vector2F = Vector2F()

    private val speedMultiplier: Float = 4F

    private val coolDownTimer: Int = 50
    private var coolDown: Int = 0

//    private val text: Text = Text(position.x.toInt(), position.y.toInt(), "Player", SpriteFont.FontColor.WHITE, SpriteFont.FontType.NORMAL, 32, 32)
//    private val speedText: Text = Text(position.x.toInt(), position.y.toInt() + 32, "Speed", SpriteFont.FontColor.WHITE, SpriteFont.FontType.NORMAL, 32, 32)
//
//    private val cross = Vector2F(0F, 0F)
//    private val crossDistance = 64

//    private val bullets: CopyOnWriteArrayList<Bullet> = CopyOnWriteArrayList()

    override fun update(delta: Long, alpha: Long) {
        position.add(Input.updateMovement().multiply(speedMultiplier, speedMultiplier))

        this.x = position.getRoundX()
        this.y = position.getRoundY()

        // Bullets
        cross = Input.updateFire()
        cross.multiply(64F, 64F)

        if (!cross.isNull()) {
            if (coolDown <= 0) {
                BulletLayer.bullets.add(BulletLayer.Bullet(position.copy(), cross.copy(), BulletLayer.Bullet.BulletType.NORMAL_RED))
                coolDown = coolDownTimer
            }
        }
        coolDown--
    }

    override fun render(graphics: Graphics) {
        // Player
        graphics.color = Color.GREEN
        graphics.fillOval(position.x.toInt() - 64 / 2, position.y.toInt() - 64 / 2, 64 / 2, 64 / 2)

        renderText(graphics, "$coolDown", x, y, false, fontWidth = 32, fontHeight = 32, fontType = SpriteFont.FontType.NORMAL, fontColor = SpriteFont.FontColor.BLACK)

        // Bullet cross
        if (!cross.isNull()) {
            graphics.color = Color.CYAN
            graphics.fillRect((position.getRoundX() - 64 / 2) + cross.getRoundX(), (position.getRoundY() - 64 / 2) + cross.getRoundY(), 64 / 2, 64 / 2)
        }
    }
}