package de.sakul6499.githubgameoff.engine.event

import de.sakul6499.githubgameoff.engine.Updateable

abstract class UpdateableEvent(name: String): Event(name), Updateable {
    override fun update(delta: Long, alpha: Long) {
        if(shellTrigger()) trigger()
    }

    abstract fun shellTrigger(): Boolean
}