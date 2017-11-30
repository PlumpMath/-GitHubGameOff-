package de.sakul6499.githubgameoff.engine.entity

import de.sakul6499.githubgameoff.engine.maths.Vector2F

abstract class Entity(val name: String? = null, var position: Vector2F = Vector2F()) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Entity) return false

        if (name != other.name) return false
        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + position.hashCode()
        return result
    }
}
