package de.sakul6499.githubgameoff.game.state

import de.sakul6499.githubgameoff.game.Renderable
import de.sakul6499.githubgameoff.game.Updateable

interface GameState : Updateable, Renderable {
    val name: String
}