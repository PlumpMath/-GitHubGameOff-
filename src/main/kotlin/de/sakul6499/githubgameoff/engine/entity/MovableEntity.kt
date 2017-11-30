package de.sakul6499.githubgameoff.engine.entity

import de.sakul6499.githubgameoff.engine.graphics.Updateable
import de.sakul6499.githubgameoff.engine.maths.Direction
import de.sakul6499.githubgameoff.engine.maths.Vector2F

abstract class MovableEntity(name: String? = null, position: Vector2F = Vector2F(), val movementIncrement: Float = 0.05F, val maxMovementSpeed: Float = 1F) : Entity(name, position), Updateable {
    val movement: Vector2F = Vector2F()

    override fun update(delta: Long, alpha: Long) {
        updateMovement()
    }

    fun updateMovement() {
        if (increaseUp()) {
            movement.x += movementIncrement
            if (movement.x > maxMovementSpeed) movement.x = maxMovementSpeed
        } else if (increaseDown()) {
            movement.x -= movementIncrement
            if (movement.x < -maxMovementSpeed) movement.x = -maxMovementSpeed
        } else {
            if (movement.x >= movementIncrement) {
                movement.x -= movementIncrement
            } else if (movement.x <= -movementIncrement) {
                movement.x += movementIncrement
            }
        }

        if (increaseRight()) {
            movement.y += movementIncrement
            if (movement.y > maxMovementSpeed) movement.y = maxMovementSpeed
        } else if (increaseLeft()) {
            movement.y -= movementIncrement
            if (movement.y < -maxMovementSpeed) movement.y = -maxMovementSpeed
        } else {
            if (movement.y >= movementIncrement) {
                movement.y -= movementIncrement
            } else if (movement.y <= -movementIncrement) {
                movement.y += movementIncrement
            }
        }

        val oldPosition = position.copy()
        position.add(movement)
        if (!checkPosition(position)) position = oldPosition
    }

    abstract fun increaseUp(): Boolean
    abstract fun increaseDown(): Boolean
    abstract fun increaseLeft(): Boolean
    abstract fun increaseRight(): Boolean

    abstract fun checkPosition(position: Vector2F): Boolean

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
}