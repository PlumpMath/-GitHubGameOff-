package de.sakul6499.githubgameoff

import com.google.gson.Gson
import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferStrategy
import java.io.File
import javax.swing.JFrame

class GameMain {
    private val cwd: File = File(".")
    private val gameConfigFile: File = File(cwd, "settings.json")
    private val gameConfig: GameConfig

    private val title: String = "#GitHubGameOff"
    private val frame: JFrame = JFrame()

    private var thread: Thread

    init {
        println("CWD: ${cwd.absolutePath} / ${cwd.canonicalPath}")
        if (!gameConfigFile.exists()) {
            println("Warning: No game config found [${gameConfigFile.absolutePath} / ${gameConfigFile.canonicalPath}]")
            println("Creating default one ...")

            gameConfig = GameConfig()
            gameConfigFile.writeText(Gson().toJson(gameConfig))
        } else {
            gameConfig = Gson().fromJson(gameConfigFile.readText(), GameConfig::class.java)
        }
        println("Game Config:")
        println(gameConfig)

        if (!gameConfig.validate()) {
            println("Game Config is invalid!")
            gameConfig.validationResultErrors.forEach {
                println(" -> ${it.text}")
            }

            throw IllegalStateException("Game Config is invalid!")
        }

        // ###
        // # Setup window / game frame
        // ###
        println("Initializing window ...")
        frame.size = Dimension(gameConfig.width, gameConfig.height)
        frame.layout = null
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.isLocationByPlatform = false
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame.cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), Point(0, 0), "emptyCursor")

        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosed(e: WindowEvent?) {
                stop()
            }
        })

        // ###
        // # Main Thread
        // ###
        thread = Thread(Runnable {
            var bufferStrategy: BufferStrategy? = null

            var lastTime = System.nanoTime()
            val nsPerTick = 1000000000 / gameConfig.fpsLimit
            var lastTimer = System.currentTimeMillis()
            var deltaTime = 0.0

            var forceRender = false

            var ups = 0
            var fps = 0

            while (!Thread.currentThread().isInterrupted) {
                val now = System.nanoTime()
                deltaTime += (now - lastTime) / nsPerTick
//                println(deltaTime)
//                if(deltaTime == 0.0) deltaTime = (now - lastTime).toDouble()
                lastTime = now

                // ###
                // # Update
                // ###

//                Thread.sleep(500)
                // ...
                ups++

                if (forceRender || deltaTime >= 1) {
                    if (forceRender) forceRender = false

                    if (bufferStrategy == null) {
                        println("Creating buffer strategy!")

                        // Double buffering
                        frame.createBufferStrategy(2)
                        bufferStrategy = frame.bufferStrategy
                    }

                    val graphics: Graphics2D = bufferStrategy!!.drawGraphics as Graphics2D

                    // ###
                    // # Draw
                    // ###
                    graphics.clearRect(0, 0, gameConfig.width, gameConfig.height)

                    graphics.color = Color.black
                    graphics.fillRect(0, 0, gameConfig.width, gameConfig.height)

                    // game state manager
                    // mouse & keyboard & input handler + cursor drawing

                    graphics.dispose()
                    bufferStrategy.show()

                    fps++
                    deltaTime--
                }

                if (System.currentTimeMillis() - lastTimer >= 1000) {
                    lastTimer += 1000
//                    forceRender = true

                    frame.title = "$title [$ups UPS|FPS $fps] {$deltaTime delta}"

                    ups = 0
                    fps = 0
                }
            }
        })
    }

    fun start() {
        if (thread.isAlive) return

        frame.isVisible = true
        thread.start()
    }

    fun stop() {
        if (!thread.isAlive) return

        frame.isVisible = false
        thread.interrupt()
    }
}