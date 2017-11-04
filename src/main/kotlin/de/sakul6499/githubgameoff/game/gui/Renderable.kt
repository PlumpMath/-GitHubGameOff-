package de.sakul6499.githubgameoff.game.gui

import java.awt.Graphics

interface Renderable {
    fun render(deltaTime: Double, graphics: Graphics)
}