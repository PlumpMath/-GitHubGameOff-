@file:JvmName("Launcher")

package de.sakul6499.githubgameoff

fun main(args: Array<String>) {
//    Runtime.getRuntime().addShutdownHook(Thread(Runnable { GameMain.instance.stop() }))
//
//    GameMain.instance.start()

    val nsPerTick = 1000000000 / 60
    var lastTime = System.nanoTime()
    var lastTimer = System.currentTimeMillis()

    var deltaTime: Double = 0.0

    println(nsPerTick)

    var run: Boolean = true
    while (run) {
        val now = System.nanoTime()
        println("$now <-> $lastTime")
        println(now - lastTime)
        val increment: Long = (now - lastTime) / nsPerTick
        println("Increment: $increment")
        deltaTime += increment

//            println(deltaTime)

        if (deltaTime >= 1) {
            println("Render [$deltaTime]")

            Thread.sleep(100)

            deltaTime--
        }

        if (System.currentTimeMillis() - lastTimer >= 1000) {
            lastTimer += 1000

            println("Sync Update [$lastTimer]")
        }

        lastTime = now
        if (Thread.interrupted()) run = false
    }
}
