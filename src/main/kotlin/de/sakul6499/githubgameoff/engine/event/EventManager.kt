package de.sakul6499.githubgameoff.engine.event

import de.sakul6499.githubgameoff.engine.Updateable

class EventManager private constructor(): Updateable {

    companion object {
        val instance: EventManager = EventManager()
        fun GetInstance(): EventManager = instance
    }

    val events: MutableList<Event> = mutableListOf()

    fun registerEvent(event: Event) {
        if(eventExists(event.name)) throw IllegalStateException("$event is already register'd!")
        events += event
    }

    fun getEvent(name: String): Event = events.find { it.name == name } ?: throw IllegalStateException("Event $name not found!")
    fun eventExists(name: String): Boolean = events.any{it.name == name}

    fun trigger(name: String): Boolean = getEvent(name).onEvent()

    override fun update(delta: Long, alpha: Long) {
        events.filter { it is UpdateableEvent }.forEach { (it as UpdateableEvent).update(delta, alpha) }
    }
}