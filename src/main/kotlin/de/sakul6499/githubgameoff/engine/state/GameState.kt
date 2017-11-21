package de.sakul6499.githubgameoff.engine.state

import de.sakul6499.githubgameoff.engine.Renderable
import de.sakul6499.githubgameoff.engine.Updateable

interface GameState : Updateable, Renderable {
    val name: String
}