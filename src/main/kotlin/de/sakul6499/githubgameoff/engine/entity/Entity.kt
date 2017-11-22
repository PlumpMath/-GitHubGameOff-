package de.sakul6499.githubgameoff.engine.entity

import de.sakul6499.githubgameoff.engine.graphics.Updateable
import de.sakul6499.githubgameoff.engine.maths.Direction
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import java.util.*

abstract class Entity(internal var position: Vector2F = Vector2F(), var speedMultiplier: Float = 0.05F, var maxSpeed: Float = 1F) : Updateable {
    val entityID: UUID = UUID.randomUUID()

    internal var movement: Vector2F = Vector2F()

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