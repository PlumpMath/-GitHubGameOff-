package de.sakul6499.githubgameoff.engine

import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwSwapBuffers
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import java.awt.Dimension
import java.awt.Point
import java.awt.Toolkit
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

abstract class EngineBase(val gameName: String, var width: Int, var height: Int) {

    var state: EngineState = EngineState.INITIALIZING
        private set(value) {
            val message = "Engine state changed from $field to $value!"
            field = value

            if(value == EngineState.CRASHED) throw IllegalStateException("$message ${if(errorMessage != null) errorMessage else ""}") else System.out.println(message)
        }
    private var errorMessage: String? = null

    private var window: JFrame = JFrame()

    var UPS: Int = 0
        private set
    var FPS: Int = 0
        private set

    var paused: Boolean = false

    init {
        println("Initializing $gameName ...")

        init()
        loop()
        deinit()
    }

    private fun init() {
        println("Initializing window ...")
        window.size = Dimension(width, height)
        window.title = gameName
        window.layout = null
        window.isResizable = false
        window.setLocationRelativeTo(null)
        window.isLocationByPlatform = false
        window.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        window.cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), Point(0, 0), "emptyCursor")

        window.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                GameMain.pauseGame()
                GameMain.stop()
            }

//        // Setup error callback
//        GLFWErrorCallback.createPrint(System.err).set()
//
//        // Init GLFW
//        if(!GLFW.glfwInit()) {
//            errorMessage = "GLFW Init failed"
//            state = EngineState.CRASHED
//        }
//
//        // Set default window hints
//        GLFW.glfwDefaultWindowHints()
//        // First make window invisible
//        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
//        // Make window resizable
//        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)
//
//        // Create window
//        window = GLFW.glfwCreateWindow(width, height, gameName, MemoryUtil.NULL, MemoryUtil.NULL)
//        // validate window
//        if(window == MemoryUtil.NULL) {
//            errorMessage = "GLFW window creating failed"
//            state = EngineState.CRASHED
//        }
//
//        // Register KeyCallback
//        GLFW.glfwSetKeyCallback(window, { window, key, scancode, action, mods -> if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) GLFW.glfwSetWindowShouldClose(window, true) })
//
//        // Get Thread stack and push new frame
//        val stack: MemoryStack = MemoryStack.stackPush()
//        try {
//            val pWidth = stack.mallocInt(1) // int*
//            val pHeight = stack.mallocInt(1) // int*
//
//            // Get the window size [passed to create window]
//            GLFW.glfwGetWindowSize(window, pWidth, pHeight)
//
//            // Get the resolution of the primary monitor
//            val vidMode: GLFWVidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())
//
//            // Center the window
//            GLFW.glfwSetWindowPos(
//                    window,
//                    (vidMode.width() - pWidth[0]) / 2,
//                    (vidMode.height() - pHeight[0]) / 2
//            )
//        } finally {
//            // pop stack
//            stack.pop()
//        }
//
//        // Open-GL Context
//        GLFW.glfwMakeContextCurrent(window)
//        // V-Sync
//        GLFW.glfwSwapInterval(1)
//
//        // Show window
//        GLFW.glfwShowWindow(window)
    }

    private fun loop() {
//        // Create GL Capabilities
//        GL.createCapabilities()
//
//        // Set viewport
//        GL11.glViewport(0, 0, width, height)
//
//        // Set the clear color
//        GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f)
//
//        // Run the rendering loop until the user has attempted to close
//        // the window or has pressed the ESCAPE key.
//        while (!glfwWindowShouldClose(window.toLong())) {
//            // clear the framebuffer
//            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
//
//
//            // swap the color buffers
//            glfwSwapBuffers(window)
//
//            // Poll for window events. The key callback above will only be
//            // invoked during this call.
//            glfwPollEvents()
//        }
    }

    private fun deinit() {
//        // Free the window callbacks and destroy the window
//        Callbacks.glfwFreeCallbacks(window)
//        GLFW.glfwDestroyWindow(window)
//
//        // Terminate GLFW and free the error callback
//        GLFW.glfwTerminate()
//        GLFW.glfwSetErrorCallback(null).free()
    }

    fun shutdown() {

    }

    fun crash(message: String? = null) {
        if(message != null) errorMessage = message
        state = EngineState.CRASHED
    }
}