package de.sakul6499.githubgameoff.engine

import com.google.gson.Gson
import de.sakul6499.githubgameoff.engine.asset.SpriteFont
import de.sakul6499.githubgameoff.engine.event.EventManager
import de.sakul6499.githubgameoff.engine.graphics.Screen
import de.sakul6499.githubgameoff.engine.input.ControllerHandler
import de.sakul6499.githubgameoff.engine.input.KeyboardHandler
import de.sakul6499.githubgameoff.engine.input.MouseHandler
import de.sakul6499.githubgameoff.engine.state.GameStateManager
import de.sakul6499.githubgameoff.engine.state.InGameGameState
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Toolkit
import java.awt.event.KeyEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.File
import javax.swing.JFrame

object GameMain {
    private val cwd: File = File(".")
    private val gameConfigFile: File = File(cwd, "settings.json")
    var gameConfig: GameConfig
        private set

    private val title: String = "#GitHubGameOff"
    private val frame: JFrame = JFrame()

    var UPS: Int = 0
    var FPS: Int = 0
    private var gameLoop: Thread

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
        frame.title = title
        frame.layout = null
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.isLocationByPlatform = false
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame.isUndecorated = true
        frame.cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), Point(0, 0), "emptyCursor")

        // ###
        // # Listeners
        // ###
        println("Setting up listeners ...")
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                stop()
            }
        })

        // Input Listener
        frame.addKeyListener(KeyboardHandler.INSTANCE)

        // Mouse listener
        frame.addMouseListener(MouseHandler.instance)
        frame.addMouseWheelListener(MouseHandler.instance)
        frame.addMouseMotionListener(MouseHandler.instance)

        // Controller
        ControllerHandler

        // ###
        // # Font
        // ###
        SpriteFont

        // ###
        // # Game States
        // ###
        println("Registering game states ...")
        GameStateManager.instance.registerGameState(InGameGameState(), true)

        // ###
        // # Game Loop
        // ###
        gameLoop = Thread({
            val timeStep = 1000000000 / 60
            var lastTime = System.nanoTime()
            var lastTimer = System.currentTimeMillis()
            var lag = 0L

            var currentUPS = 0
            var currentFPS = 0

            while (!Thread.currentThread().isInterrupted) {
                val delta = System.nanoTime() - lastTime
                lastTime = System.nanoTime()
                lag += delta

                // ###
                // # Handle Events
                // ###
                var alpha = lag / timeStep
                // Controller
                ControllerHandler.instance.update(delta, alpha)

                // Update events
                EventManager.instance.update(delta, alpha)

                // TODO: move to Event?
                Screen.setDebugRendering(KeyboardHandler.IsKeyPressed(KeyEvent.VK_CONTROL) && KeyboardHandler.IsKeyPressed(KeyEvent.VK_ALT))

                while (lag >= timeStep) {
                    lag -= timeStep
                    alpha = lag / timeStep

                    // ###
                    // # Update
                    // ###
                    // Update screen
                    Screen.update(delta, alpha)

                    currentUPS++
                }

                // ###
                // # Render
                // ###
                // Buffer Strategy
                if (frame.bufferStrategy == null) {
                    println("Creating buffer strategy!")
                    frame.createBufferStrategy(2)
                }

                // Graphics
                val graphics: Graphics2D = frame.bufferStrategy.drawGraphics as Graphics2D

                // Clear screen
                graphics.clearRect(0, 0, gameConfig.width, gameConfig.height)

                // Render screen
                Screen.render(graphics)

                // Finish up
                graphics.dispose()
                frame.bufferStrategy.show()

                currentFPS++

                if (System.currentTimeMillis() - lastTimer >= 1000) {
                    lastTimer += 1000

                    UPS = currentUPS
                    FPS = currentFPS

                    frame.title = "$title [UPS $UPS | $FPS FPS]"

                    currentUPS = 0
                    currentFPS = 0
                }
            }

            println("### INTERRUPTED ###")
            println("### EXIT ###")

            // Free SDL
            ControllerHandler.instance.exit()

            // Free Frame
            frame.isVisible = false
            frame.removeAll()
            frame.removeKeyListener(KeyboardHandler.INSTANCE)
            frame.removeMouseListener(MouseHandler.instance)
        }, "Game Loop")
    }

    fun start() {
        if (gameLoop.isAlive) return
        println("Starting!")

        frame.isVisible = true
        gameLoop.start()
    }

    fun stop() {
        if (!gameLoop.isAlive) return
        println("Stopping!")

        frame.isVisible = false
        gameLoop.interrupt()
    }
}