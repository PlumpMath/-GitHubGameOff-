package de.sakul6499.githubgameoff.game.gui

import de.sakul6499.githubgameoff.game.input.InputHandler
import de.sakul6499.githubgameoff.game.maths.Vector2F
import java.awt.event.KeyEvent
import java.util.*

abstract class Entity(x: Float = 0.0F, y: Float = 0.0F, var speedMultiplier: Float = 0.05F, var maxSpeed: Float = 1F) : Updateable {
    val entityID: UUID = UUID.randomUUID()

    private val position: Vector2F = Vector2F(x, y)
    private val movement: Vector2F = Vector2F()

    override fun update(deltaTime: Double) {
        // TODO: Test
        if (InputHandler.IsKeyPressed(KeyEvent.VK_W)) {
            movement.x += speedMultiplier
            if (movement.x > maxSpeed) movement.x = maxSpeed
        }

        if (InputHandler.IsKeyPressed(KeyEvent.VK_S)) {
            movement.x -= speedMultiplier
            if (movement.x < -maxSpeed) movement.x = -maxSpeed
        }

        if (InputHandler.IsKeyPressed(KeyEvent.VK_A)) {
            movement.y -= speedMultiplier
            if (movement.y < maxSpeed) movement.y = -maxSpeed
        }

        if (InputHandler.IsKeyPressed(KeyEvent.VK_S)) {
            movement.y += speedMultiplier
            if (movement.y > maxSpeed) movement.y = maxSpeed
        }

        if (!movement.isNull()) {
            position.add(movement)
        }
    }

    override fun equals(other: Any?): Boolean = if (other == null) false else {
        if (other is Entity) other.entityID == entityID else false
    }
}