@file:JvmName("Launcher")

package de.sakul6499.githubgameoff

fun main(args: Array<String>) {
    val gameMain = GameMain()

    Runtime.getRuntime().addShutdownHook(Thread(Runnable { gameMain.stop() }))

    gameMain.start()
}
