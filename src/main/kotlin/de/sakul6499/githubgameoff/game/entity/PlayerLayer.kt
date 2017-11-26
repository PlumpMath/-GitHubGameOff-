package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.graphics.Layer
import de.sakul6499.githubgameoff.engine.graphics.asset.Image
import de.sakul6499.githubgameoff.engine.graphics.asset.Images
import de.sakul6499.githubgameoff.engine.graphics.asset.StagedImage
import de.sakul6499.githubgameoff.engine.input.Input
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.game.BackgroundLayer
import java.awt.Color
import java.awt.Graphics
import javax.imageio.ImageIO

object PlayerLayer : Layer("Player", 5, BackgroundLayer.v0, BackgroundLayer.v1) {
    override val isActive: Boolean = true

    private val size: Int = 64

    internal val position: Vector2F = Vector2F(getWidth() / 2, getHeight() / 2)
    private var cross: Vector2F = Vector2F()
    private val crossMultiply = 64F
    private val crossTolerance = 0.25F * crossMultiply

    private val speedMultiplier: Float = 4F

    private val coolDownTimer: Int = 50
    private var coolDown: Int = 0

    private var counter: Int = 0

    private enum class States(val stage: Int) {
        NORMAL(0),
        SMALL(1),
        BIG(2),
    }

    private var currentState: States = States.NORMAL
    private var previousState: States = States.BIG

    fun GetMidPosition(): Vector2F = Vector2F(position.x + size / 2, position.y + size / 2)

    init {
        println("INIT: $position")
        println("Range: ${BackgroundLayer.getBox()}")

        Images.registerImage("ice_soul", StagedImage(arrayOf(
                Image(ImageIO.read(this::class.java.getResource("/tiles/entity/ice_soul/0.png"))),
                Image(ImageIO.read(this::class.java.getResource("/tiles/entity/ice_soul/1.png"))),
                Image(ImageIO.read(this::class.java.getResource("/tiles/entity/ice_soul/2.png")))
        )))
        Images.registerImage("cross", Image(ImageIO.read(this::class.java.getResource("/tiles/cross/0.png"))))
    }

    override fun update(delta: Long, alpha: Long) {
        if (counter++ > 25) {
            counter = 0

            val newState: States = when (currentState) {
                PlayerLayer.States.NORMAL -> if (previousState == States.SMALL) States.BIG else States.SMALL
                PlayerLayer.States.SMALL, PlayerLayer.States.BIG -> States.NORMAL
            }
            previousState = currentState
            currentState = newState
        }

        position.add(Input.updateMovement().multiply(speedMultiplier, speedMultiplier))

        // Bullets
        cross = Input.updateFire()
        cross.multiply(crossMultiply)

        if (!cross.isNull(crossTolerance)) {
            if (coolDown <= 0) {
                BulletLayer.bullets.add(BulletLayer.Bullet(position.copy(), cross.copy(), BulletLayer.Bullet.BulletType.NORMAL_RED))
                coolDown = coolDownTimer
            }
        }
        if (coolDown > 0) coolDown--
    }

    override fun render(graphics: Graphics) {
        // Player
        renderImage(graphics, Images.getImage("ice_soul", currentState.stage), position.x.toInt() - size / 2, position.y.toInt() - size / 2, size, size)
        renderBox(graphics, position.getRoundX() - size / 2, position.getRoundY() - size / 2, size, size, Color.RED)

        // Bullet cross
        if (!cross.isNull(crossTolerance)) {
            renderImage(graphics, Images.getImage("cross"), (position.getRoundX() - size / 2) + cross.getRoundX(), (position.getRoundY() - size / 2) + cross.getRoundY(), size / 2, size / 2)
        }
    }
}