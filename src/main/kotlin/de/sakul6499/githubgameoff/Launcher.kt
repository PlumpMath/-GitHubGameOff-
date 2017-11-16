@file:JvmName("Launcher")

package de.sakul6499.githubgameoff

import de.sakul6499.githubgameoff.engine.GameMain
import de.sakul6499.githubgameoff.engine.Screen
import de.sakul6499.githubgameoff.game.GameLayer

fun main(args: Array<String>) {
    Runtime.getRuntime().addShutdownHook(Thread({ GameMain.stop() }))

    Screen.registerLayer(GameLayer)
    GameMain.start()
}
