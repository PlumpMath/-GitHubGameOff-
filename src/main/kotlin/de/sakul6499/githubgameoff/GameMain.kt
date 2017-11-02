package de.sakul6499.githubgameoff

import com.google.gson.Gson
import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferStrategy
import java.io.File
import javax.swing.JFrame

class GameMain {
    companion object {
        val cwd: File = File(".")
        val gameConfigFile: File = File(cwd, "settings.json")
        lateinit var gameConfig: GameConfig
            private set
    }

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

        // ###
        // # Listeners
        // ###
        println("Setting up listeners ...")
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosed(e: WindowEvent?) {
                stop()
            }
        })

        // Input Listener
        frame.addKeyListener(InputHandler.instance)

        // Mouse listener
        frame.addMouseListener(MouseHandler.instance)
        frame.addMouseWheelListener(MouseHandler.instance)
        frame.addMouseMotionListener(MouseHandler.instance)

        // Controller
        ControllerHandler

        // ###
        // # Game States
        // ###
        println("Registering game states ...")
        GameStateManager.instance.registerGameState(InGameGameState(), true)

        // ###
        // # Main Thread
        // ###
        thread = Thread(Runnable {
            var bufferStrategy: BufferStrategy? = null

            var lastTime = System.nanoTime()
            val nsPerTick = 1000000000 / gameConfig.deltaLimit
            var lastTimer = System.currentTimeMillis()
            var deltaTime = 0.0

            var forceRender = false

            // defines weather or not the fps limitation for the delta time is used
            var limitless: Boolean
            var ups = 0
            var fps = 0

            while (!Thread.currentThread().isInterrupted) {
                // ###
                // # Delta Time
                // ###
                val now = System.nanoTime()
                deltaTime += (now - lastTime) / nsPerTick
                // if the 'limited calculation' fails [<= 0.0], calculate without limit:
                limitless = if (deltaTime <= 0.0) {
                    deltaTime = (now - lastTime).toDouble()
                    true
                } else false
                lastTime = now

                // ###
                // # Update
                // ###

                // Controller
                ControllerHandler.instance.update()

                // Update current game state
                GameStateManager.instance.updateCurrent(deltaTime)

                ups++

                if (forceRender || deltaTime >= 1) {
                    if (bufferStrategy == null) {
                        println("Creating buffer strategy!")

                        // Double buffering
                        frame.createBufferStrategy(2)
                        bufferStrategy = frame.bufferStrategy

                        continue
                    }

                    val graphics: Graphics2D = bufferStrategy.drawGraphics as Graphics2D

                    // ###
                    // # Draw
                    // ###
                    // Clear screen
                    graphics.clearRect(0, 0, gameConfig.width, gameConfig.height)

                    // Render current game state
                    GameStateManager.instance.renderCurrent(deltaTime, graphics)

                    // Render cursor
                    graphics.color = if (MouseHandler.PressedAny()) Color.MAGENTA else Color.white
                    graphics.fillRect(MouseHandler.MousePosition.x, MouseHandler.MousePosition.y, 64, 64)

                    // Finish up
                    graphics.dispose()
                    bufferStrategy.show()

                    fps++

                    if (forceRender) {
                        forceRender = false
                    } else {
                        deltaTime--
                        if (deltaTime < 0) deltaTime = 0.0
                    }
                }

                if (System.currentTimeMillis() - lastTimer >= 1000) {
                    lastTimer += 1000
                    forceRender = true

                    frame.title = "$title [$ups UPS|FPS $fps] {$deltaTime delta} ${if (limitless) "#LIMITLESS" else ""}"

                    ups = 0
                    fps = 0
                }
            }

            println("### INTERRUPTED ###")
            println("### EXIT ###")
            ControllerHandler.instance.exit()
        })
    }

    fun start() {
        if (thread.isAlive) return
        println("Starting!")

        frame.isVisible = true
        thread.start()
    }

    fun stop() {
        if (!thread.isAlive) return
        println("Stopping!")

        frame.isVisible = false
        thread.interrupt()
    }
}