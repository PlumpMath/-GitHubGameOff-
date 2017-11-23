package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.graphics.Renderable
import de.sakul6499.githubgameoff.engine.graphics.Updateable
import de.sakul6499.githubgameoff.engine.graphics.asset.Images
import de.sakul6499.githubgameoff.engine.graphics.asset.StagedImage
import de.sakul6499.githubgameoff.engine.maths.*
import java.awt.Graphics
import javax.imageio.ImageIO

class Enemy(val position: Vector2F, private val size: Int) : Updateable, Renderable {

    private var counter = 0

    private val maxStages = 1
    private var stage = 0
    private var direction = 0

    init {
        Images.registerImage("eye_0", StagedImage.CreateRotationBasedStagedImage(ImageIO.read(this.javaClass.getResource("/tiles/entity/eye/0.png")), 4, Math.PI / 2))
        Images.registerImage("eye_1", StagedImage.CreateRotationBasedStagedImage(ImageIO.read(this.javaClass.getResource("/tiles/entity/eye/1.png")), 4, Math.PI / 2))
    }

    var boxVector: VectorBox<Vector<Float>> = VectorBox(position.copy().subtract(size.toFloat() / 2), position.copy().add(size.toFloat() / 2))
        private set
        get() {
            field = VectorBox(position.copy().subtract(size.toFloat() / 2), position.copy().add(size.toFloat() / 2))
            return field
        }

    override fun update(delta: Long, alpha: Long) {
        if (counter++ > 25) {
            updateStage()
            counter = 0
        }

        updateMovement()
    }

    private fun updateMovement() {
        val diff = PlayerLayer.position.diff(position)

        calculateFacing(diff)

        position.add(diff.divide(218F))
    }

    private fun calculateFacing(diff: Vector<Float>) {
        val x0 = if (diff.x < 0) -diff.x else diff.x
        val y0 = if (diff.y < 0) -diff.y else diff.y

        direction = if (x0 > y0) {
            if (diff.x > 0) 2 else if (diff.x < 0) 0 else 0
        } else {
            if (diff.y > 0) 3 else if (diff.y < 0) 1 else 0
        }
    }

    private fun updateStage() {
        if (stage + 1 > maxStages) {
            stage = 0
        } else {
            stage++
        }
    }

    override fun render(graphics: Graphics) {
        graphics.drawImage(Images.getImage("eye_$stage", direction), position.getRoundX() - size / 2, position.getRoundY() - size / 2, size, size, null)
    }
}