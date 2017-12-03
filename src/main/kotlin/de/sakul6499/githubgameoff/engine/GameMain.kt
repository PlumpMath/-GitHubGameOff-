package de.sakul6499.githubgameoff.engine

import de.sakul6499.githubgameoff.engine.graphics.Screen
import de.sakul6499.githubgameoff.engine.graphics.asset.SpriteFont
import de.sakul6499.githubgameoff.engine.input.ControllerHandler
import de.sakul6499.githubgameoff.engine.input.Input
import de.sakul6499.githubgameoff.engine.input.KeyboardHandler
import de.sakul6499.githubgameoff.engine.input.MouseHandler
import de.sakul6499.githubgameoff.engine.state.GameStateManager
import de.sakul6499.githubgameoff.engine.state.InGameGameState
import de.sakul6499.githubgameoff.game.GameConfig
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
    var gameConfig: GameConfig = Engine.getJSONConfigOrCreate("config", GameConfig::class.java, GameConfig())
        private set

    private val title: String = "#GitHubGameOff"
    private val frame: JFrame = JFrame()

    var UPS: Int = 0
    var FPS: Int = 0
    private var gameLoop: Thread

    private var pause: Boolean = false

    init {
        println("CWD: ${cwd.absolutePath} / ${cwd.canonicalPath}")

        val errors = gameConfig.validate()
        if (errors.isNotEmpty()) {
            println("Game Config is invalid!")
            println("Errors: ")
            errors.forEach { println(it) }

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
//        frame.isUndecorated = true
        frame.cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), Point(0, 0), "emptyCursor")
//        frame.addWindowFocusListener(object: WindowFocusListener {
//            override fun windowGainedFocus(e: WindowEvent?) {
//                unpauseGame()
//            }
//
//            override fun windowLostFocus(e: WindowEvent?) {
//                pauseGame()
//            }
//        })
//        frame.addWindowStateListener { e: WindowEvent ->
//            println("EVENT: $e")
//            when (e.newState) {
//                WindowEvent.WINDOW_DEICONIFIED, WindowEvent.WINDOW_GAINED_FOCUS -> unpauseGame()
//                WindowEvent.WINDOW_ICONIFIED, WindowEvent.WINDOW_LOST_FOCUS -> pauseGame()
//            }
//        }
//        frame.addWindowStateListener(object: WindowStateListener {
//            override fun windowStateChanged(e: WindowEvent?) {
//                println(e)
//            }
//        })

        // ###
        // # Listeners
        // ###
        println("Setting up listeners ...")
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                pauseGame()
                stop()
            }
//
//            override fun windowGainedFocus(e: WindowEvent?) {
//                unpauseGame()
//            }

//            override fun windowDeiconified(e: WindowEvent?) {
////                unpauseGame()
////                println("B")
//
//
//                // TODO broke
//                unpauseGame()
//            }

//            override fun windowActivated(e: WindowEvent?) {
//                unpauseGame()
//            }
//
//            override fun windowDeactivated(e: WindowEvent?) {
//                pauseGame()
//            }

//            override fun windowIconified(e: WindowEvent?) {
//                if (!pause) pauseGame()
////                println("A")
//            }

//            override fun windowLostFocus(e: WindowEvent?) {
//                pauseGame()
//            }


//
//            override fun windowStateChanged(e: WindowEvent?) {
//                println("Event: $e")
//            }

//            override fun windowClosed(e: WindowEvent?) {
//                super.windowClosed(e)
//            }
//
//            override fun windowOpened(e: WindowEvent?) {
//                super.windowOpened(e)
//            }
        })

        // Mouse listener
        frame.addMouseListener(MouseHandler.instance)
//        frame.addMouseWheelListener(MouseHandler.instance)
        frame.addMouseMotionListener(MouseHandler.instance)

        // Input
        frame.addKeyListener(Input)

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
            // Set screen offset by title bar
            Screen.offset = frame.insets.top

            val timeStep = 1000000000 / 60
            var lastTime = System.nanoTime()
            var lastTimer = System.currentTimeMillis()
            var lag = 0L

            var currentUPS = 0
            var currentFPS = 0

            var alpha: Long

            while (!Thread.currentThread().isInterrupted) {
                val delta = System.nanoTime() - lastTime
                lastTime = System.nanoTime()
                lag += delta

                if (!pause) {
                    while (lag >= timeStep) {
                        lag -= timeStep
                        alpha = lag / timeStep

                        // ###
                        // # Update
                        // ###
                        // Controller
                        ControllerHandler.instance.update(delta, alpha)

                        // Update events
//                    EventManager.instance.update(delta, alpha)

                        Screen.setDebugRendering(KeyboardHandler.IsKeyPressed(KeyEvent.VK_CONTROL) && KeyboardHandler.IsKeyPressed(KeyEvent.VK_ALT))

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
            }

            println("### INTERRUPTED ###")
            println("### EXIT ###")

            // Free SDL
            Input.exit()

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

    //    fun pauseGame(minimize: Boolean = false) {
    fun pauseGame() {
        if (!pause) {
            println("Pause game!")
            pause = true

//            if (minimize) frame.extendedState = Frame.ICONIFIED
        }
    }

    fun unpauseGame() {
        if (pause) {
            println("Unpause game!")
            pause = false

//            if (frame.extendedState == Frame.ICONIFIED) {
//                frame.extendedState = Frame.NORMAL
//                frame.requestFocus()
//            }
        }
    }
}