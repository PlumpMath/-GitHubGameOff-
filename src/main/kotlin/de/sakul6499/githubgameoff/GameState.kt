package de.sakul6499.githubgameoff

import java.awt.Graphics

interface GameState {
    val name: String

    fun update(deltaTime: Double)
    fun render(deltaTime: Double, graphics: Graphics)
}