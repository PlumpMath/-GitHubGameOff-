package de.sakul6499.githubgameoff.engine.event

abstract class Event(val name: String) {
    fun trigger() {
        if(!onEvent()) println("$this failed!")
    }

    abstract internal fun onEvent(): Boolean

    override fun toString(): String = "Event(name='$name')"
}