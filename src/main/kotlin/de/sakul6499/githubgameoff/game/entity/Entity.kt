package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.game.Updateable
import de.sakul6499.githubgameoff.game.input.KeyboardHandler
import de.sakul6499.githubgameoff.game.maths.Vector2F
import java.awt.event.KeyEvent
import java.util.*

abstract class Entity(x: Float = 0.0F, y: Float = 0.0F, var speedMultiplier: Float = 0.05F, var maxSpeed: Float = 1F) : Updateable {
    val entityID: UUID = UUID.randomUUID()

    internal var position: Vector2F = Vector2F(x, y)
    internal var movement: Vector2F = Vector2F()

    /*
    eg. Before movement

    return false -> cancel update
     */
    open fun preUpdate(): Boolean = true

    override fun update(deltaTime: Double) {
        if (!preUpdate()) return

        val backupMovement = movement.copy()

        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_W)) {
            movement.y -= speedMultiplier
            if (movement.y < -maxSpeed) movement.y = -maxSpeed
        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_S)) {
            movement.y += speedMultiplier
            if (movement.y > maxSpeed) movement.y = maxSpeed
        } else {
            when {
                movement.y > 0.1 -> movement.y -= speedMultiplier
                movement.y < -0.1 -> movement.y += speedMultiplier
                else -> movement.y = 0F
            }
        }

        if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_A)) {
            movement.x -= speedMultiplier
            if (movement.x < -maxSpeed) movement.x = -maxSpeed
        } else if (KeyboardHandler.IsKeyPressed(KeyEvent.VK_D)) {
            movement.x += speedMultiplier
            if (movement.x > maxSpeed) movement.x = maxSpeed
        } else {
            when {
                movement.x > 0.1 -> movement.x -= speedMultiplier
                movement.x < -0.1 -> movement.x += speedMultiplier
                else -> movement.x = 0F
            }
        }

        if (!postUpdate()) {
            movement = backupMovement
            return
        }

        if (!movement.isNull()) position.add(movement)
    }

    /*
    eg. Before applying movement

    return false -> cancel update
     */
    open fun postUpdate(): Boolean = true

    fun getFacing(): Direction {
        val f0: Direction = if (movement.x == 0F) Direction.NULL else if (movement.x < 0) Direction.LEFT else Direction.RIGHT
        val f1: Direction = if (movement.y == 0f) Direction.NULL else if (movement.y < 0) Direction.UP else Direction.DOWN

        return if (f0 == Direction.NULL || f1 == Direction.NULL) {
            if (f0 == Direction.NULL) {
                f1
            } else {
                f0
            }
        } else {
            Direction.valueOf("${f1}_$f0")
        }
    }

    override fun equals(other: Any?): Boolean = if (other == null) false else {
        if (other is Entity) other.entityID == entityID else false
    }

    override fun hashCode(): Int {
        var result = speedMultiplier.hashCode()
        result = 31 * result + maxSpeed.hashCode()
        result = 31 * result + entityID.hashCode()
        result = 31 * result + position.hashCode()
        result = 31 * result + movement.hashCode()
        return result
    }
}