package de.sakul6499.githubgameoff.engine.input

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyboardHandler : KeyListener {

    companion object {
        val INSTANCE: KeyboardHandler = KeyboardHandler()

        private val keys: MutableMap<Int, Boolean> = mutableMapOf()

        /**
         * Use KeyEvent.VK_*
         */
        fun IsKeyPressed(keyCode: Int): Boolean = keys[keyCode] == true
    }

    override fun keyPressed(e: KeyEvent) {
        keys.put(e.keyCode, true)
    }

    override fun keyReleased(e: KeyEvent) {
        keys.put(e.keyCode, false)
    }

    override fun keyTyped(e: KeyEvent?) = Unit
}