package de.sakul6499.githubgameoff.game.state

import java.awt.Graphics

interface GameState {
    val name: String

    fun update(deltaTime: Double)
    fun render(deltaTime: Double, graphics: Graphics)
}