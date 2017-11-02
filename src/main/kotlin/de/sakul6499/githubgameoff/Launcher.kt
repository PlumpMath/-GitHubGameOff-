@file:JvmName("Launcher")

package de.sakul6499.githubgameoff

import de.sakul6499.githubgameoff.game.GameMain

fun main(args: Array<String>) {
    val gameMain = GameMain()

    Runtime.getRuntime().addShutdownHook(Thread(Runnable { gameMain.stop() }))

    gameMain.start()
}
