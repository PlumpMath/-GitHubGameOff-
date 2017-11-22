package de.sakul6499.githubgameoff.engine.input

import com.studiohartman.jamepad.ControllerAxis
import com.studiohartman.jamepad.ControllerButton
import com.studiohartman.jamepad.ControllerManager
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

    }


}