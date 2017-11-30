package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.entity.MovableEntity
import de.sakul6499.githubgameoff.engine.graphics.Renderable
import de.sakul6499.githubgameoff.engine.graphics.asset.Image
import de.sakul6499.githubgameoff.engine.graphics.asset.Images
import de.sakul6499.githubgameoff.engine.graphics.asset.StagedImage
import de.sakul6499.githubgameoff.engine.input.Input
import de.sakul6499.githubgameoff.engine.maths.BoxVector
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.engine.maths.Vector2I
import de.sakul6499.githubgameoff.game.BackgroundLayer
import java.awt.Graphics
import javax.imageio.ImageIO

class Player(position: Vector2F) : MovableEntity("Player", position, 0.25F, 5F), Renderable {
    private var inputMovement: Vector2F = Vector2F()

    private val playerSize = 64
    private val crossSize = playerSize / 2

    /**
     * Animation state counter
     */
    private var animationStateCounter = 0
    /**
     * Current animation state
     */
    private var currentState: PlayerAnimationState = PlayerAnimationState.NORMAL
    /**
     * Previous animation state
     */
    private var previousState: PlayerAnimationState = PlayerAnimationState.BIG

    private var cross: Vector2F = Vector2F()
    private var fireTolerance = 0.25F
    private var crossMultiply = playerSize.toFloat()

    private val bulletCoolDown = 50
    private var bulletCoolDownTimer = 0

    init {
        Images.registerImage("ice_soul", StagedImage(arrayOf(
                Image(ImageIO.read(this::class.java.getResource("/tiles/entity/ice_soul/0.png"))),
                Image(ImageIO.read(this::class.java.getResource("/tiles/entity/ice_soul/1.png"))),
                Image(ImageIO.read(this::class.java.getResource("/tiles/entity/ice_soul/2.png")))
        )))
        Images.registerImage("cross", Image(ImageIO.read(this::class.java.getResource("/tiles/cross/0.png"))))
    }

    override fun update(delta: Long, alpha: Long) {
        inputMovement = Input.updateMovement()
        super.update(delta, alpha)

        if (animationStateCounter++ > 25) {
            animationStateCounter = 0

            val newState: PlayerAnimationState = when (currentState) {
                PlayerAnimationState.NORMAL -> if (previousState == PlayerAnimationState.SMALL) PlayerAnimationState.BIG else PlayerAnimationState.SMALL
                PlayerAnimationState.SMALL, PlayerAnimationState.BIG -> PlayerAnimationState.NORMAL
            }
            previousState = currentState
            currentState = newState
        }

        // Cross
        val fire = Input.updateFire(fireTolerance)
        if (!fire.isNull(fireTolerance)) {
            cross = fire.multiply(crossMultiply) as Vector2F

            if (bulletCoolDownTimer <= 0) {
                bulletCoolDownTimer = bulletCoolDown

                BulletLayer.bullets.add(BulletLayer.Bullet(position.copy(), cross, BulletLayer.Bullet.BulletType.NORMAL_RED))
            }
        } else cross.nullify()

        if (bulletCoolDownTimer > 0) bulletCoolDownTimer--
    }

    override fun increaseUp(): Boolean = inputMovement.x > 0

    override fun increaseDown(): Boolean = inputMovement.x < 0

    override fun increaseLeft(): Boolean = inputMovement.y < 0

    override fun increaseRight(): Boolean = inputMovement.y > 0

    override fun checkPosition(position: Vector2F): Boolean = BackgroundLayer.getBox().isInBox(BoxVector(Vector2I(position.getRoundX(), position.getRoundY()), Vector2I(position.getRoundX() + playerSize, position.getRoundY() + playerSize)))

    override fun render(graphics: Graphics) {
        if (!cross.isNull(fireTolerance)) {
            graphics.drawImage(Images.getImage("cross"),
                    ((position.x - crossSize / 2) + cross.x).toInt(),
                    ((position.y - crossSize / 2) + cross.y).toInt(),
                    crossSize, crossSize)
        }

        graphics.renderImage(Images.getImage("ice_soul", currentState.stage), position.toVector2I(), playerSize)
    }
}