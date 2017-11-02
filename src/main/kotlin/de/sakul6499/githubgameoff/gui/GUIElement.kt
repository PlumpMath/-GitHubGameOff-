package de.sakul6499.githubgameoff.gui

import java.awt.Graphics

interface GUIElement {
    var active: Boolean

    fun render(deltaTime: Double, graphics: Graphics) {
        if (active) renderElement(deltaTime, graphics)
    }

    fun renderElement(deltaTime: Double, graphics: Graphics)
}