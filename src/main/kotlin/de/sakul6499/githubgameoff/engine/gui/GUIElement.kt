package de.sakul6499.githubgameoff.engine.gui

import java.awt.Graphics

interface GUIElement {
    var x: Int
    var y: Int

    var active: Boolean

    fun makeActive() {
        active = true
    }

    fun makeInactive() {
        active = false
    }

    fun render(deltaTime: Double, graphics: Graphics) {
        if (active) renderElement(deltaTime, graphics)
    }

    fun renderElement(deltaTime: Double, graphics: Graphics)
}