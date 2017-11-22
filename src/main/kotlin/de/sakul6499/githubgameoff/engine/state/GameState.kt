package de.sakul6499.githubgameoff.engine.state

import de.sakul6499.githubgameoff.engine.graphics.Renderable
import de.sakul6499.githubgameoff.engine.graphics.Updateable

interface GameState : Updateable, Renderable {
    val name: String
}