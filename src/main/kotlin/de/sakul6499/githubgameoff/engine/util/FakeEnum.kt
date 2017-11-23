package de.sakul6499.githubgameoff.engine.util

interface FakeEnum<T: Any> {
    fun values(): Array<T>
    fun valueOf(name: String): T?
}