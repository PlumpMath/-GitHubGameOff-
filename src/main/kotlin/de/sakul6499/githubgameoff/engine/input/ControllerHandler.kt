package de.sakul6499.githubgameoff.engine.input

import com.studiohartman.jamepad.*
import de.sakul6499.githubgameoff.engine.graphics.Updateable

class ControllerHandler private constructor() : Updateable {

    companion object {
        val instance: ControllerHandler = ControllerHandler()

        private val buttons: MutableMap<ControllerButton, Boolean> = mutableMapOf()
        private val axises: MutableMap<ControllerAxis, Float> = mutableMapOf()

        fun IsButtonPressed(button: ControllerButton): Boolean = buttons[button] == true
        fun GetAxisValue(axis: ControllerAxis): Float = axises[axis] ?: 0F
    }

    private val controllerManager: ControllerManager = ControllerManager()

    init {
        println("Launching SDL GamePad library ...")
        controllerManager.initSDLGamepad()
    }

    override fun update(delta: Long, alpha: Long) {
        controllerManager.update()
        if (controllerManager.numControllers > 0) {
            for (i in 0..controllerManager.numControllers) {
                try {
                    val controllerIndex: ControllerIndex = controllerManager.getControllerIndex(i)
                    if (controllerIndex.isConnected) {
                        ControllerButton.values().forEach { if (controllerIndex.isButtonPressed(it)) buttons.put(it, true) else buttons.put(it, false) }
                        ControllerAxis.values().forEach { axises[it] = controllerIndex.getAxisState(it) }
                    }
                } catch (e: ControllerUnpluggedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun exit() {
        println("Freeing SDL GamePad library ...")
        controllerManager.quitSDLGamepad()
    }
}