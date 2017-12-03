package de.sakul6499.githubgameoff.game

import de.sakul6499.githubgameoff.engine.Engine
import de.sakul6499.githubgameoff.engine.EngineBase

object Game: EngineBase("Souls", 1280, 720) {

    val gameConfig: GameConfig = Engine.getJSONConfigOrCreate("config", GameConfig::class.java, GameConfig())

    init {
        val errors = gameConfig.validate()
        if(errors.isNotEmpty()) {
            println("Game config is invalid!")
            println("Errors:")

            errors.forEach { println(it) }

            crash("Game config is invalid!")
        }

        width = gameConfig.width
        height = gameConfig.height
    }
}