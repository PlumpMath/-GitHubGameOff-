package de.sakul6499.githubgameoff.game

import com.google.gson.Gson
import de.sakul6499.githubgameoff.game.asset.SpriteFont
import de.sakul6499.githubgameoff.game.event.EventManager
import de.sakul6499.githubgameoff.game.gui.Text
import de.sakul6499.githubgameoff.game.input.ControllerHandler
import de.sakul6499.githubgameoff.game.input.KeyboardHandler
import de.sakul6499.githubgameoff.game.input.MouseHandler
import de.sakul6499.githubgameoff.game.state.GameStateManager
import de.sakul6499.githubgameoff.game.state.InGameGameState
import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.File
import javax.swing.JFrame

object GameMain {
        val cwd: File = File(".")
        val gameConfigFile: File = File(cwd, "settings.json")
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

                while (lag >= timeStep) {
                    lag -= timeStep
                    alpha = lag / timeStep

                    // ###
                    // # Update
                    // ###
                    // Update current game state [TODO test alpha]
                    GameStateManager.instance.update(delta, alpha)

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

                // Render current game state
                GameStateManager.instance.render(graphics)

                // Show info
                Text(0, 32, "UPS: $UPS [$currentUPS]", fontWidth = 32, fontHeight = 32).render(graphics)
                Text(0, 32 * 2, "FPS: $FPS [$currentFPS]", fontWidth = 32, fontHeight = 32).render(graphics)
                Text(0, 32 * 3, "Lag: $lag", fontWidth = 32, fontHeight = 32).render(graphics)
                Text(0, 32 * 4, "Delta: $delta", fontWidth = 32, fontHeight = 32).render(graphics)
                Text(0, 32 * 5, "Alpha: $alpha", fontWidth = 32, fontHeight = 32).render(graphics)

                // Render cursor
                graphics.color = if (MouseHandler.PressedAny()) Color.MAGENTA else Color.white
                graphics.fillRect(MouseHandler.MousePosition.x, MouseHandler.MousePosition.y, 64, 64)

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