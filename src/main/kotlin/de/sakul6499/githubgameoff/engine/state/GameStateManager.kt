package de.sakul6499.githubgameoff.engine.state

import de.sakul6499.githubgameoff.engine.Renderable
import de.sakul6499.githubgameoff.engine.Updateable
import java.awt.Graphics

class GameStateManager private constructor() : Updateable, Renderable {
    companion object {
        val instance: GameStateManager = GameStateManager()
    }

    private val gameStates: MutableList<GameState> = mutableListOf()
    private var currentGameState: String = ""

    fun registerGameState(gameState: GameState, makeCurrent: Boolean = false) {
        if (gameStates.any { it.name == gameState.name }) throw IllegalStateException("The name '${gameState.name}' is already in the registry!")

        gameStates.add(gameState)
        if (makeCurrent) currentGameState = gameState.name
    }

    fun removeGameState(name: String) {
        if (name == currentGameState) currentGameState = ""

        gameStates.removeIf { it.name == name }
    }

    fun updateCurrentGameState(name: String) {
        if (gameStates.none { it.name == name }) throw IllegalStateException("The game state '$name' is not defined in the registry!")
        currentGameState = name
    }

    override fun update(delta: Long, alpha: Long) {
        gameStates.find { it.name == currentGameState }!!.update(delta, alpha)
    }

    override fun render(graphics: Graphics) {
        gameStates.find { it.name == currentGameState }!!.render(graphics)
    }
}