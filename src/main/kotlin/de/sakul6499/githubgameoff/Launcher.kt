@file:JvmName("Launcher")

package de.sakul6499.githubgameoff

import de.sakul6499.githubgameoff.game.GameMain

fun main(args: Array<String>) {
    Runtime.getRuntime().addShutdownHook(Thread({ GameMain.stop() }))
    GameMain.start()
}
