@file:JvmName("Launcher")

package de.sakul6499.githubgameoff

import de.sakul6499.githubgameoff.engine.GameMain
import de.sakul6499.githubgameoff.engine.graphics.Screen
import de.sakul6499.githubgameoff.game.BackgroundLayer
import de.sakul6499.githubgameoff.game.entity.PlayerLayer

fun main(args: Array<String>) {
    Runtime.getRuntime().addShutdownHook(Thread({ GameMain.stop() }))

    Screen.registerLayer(BackgroundLayer)
    Screen.registerLayer(PlayerLayer)
    GameMain.start()
}
