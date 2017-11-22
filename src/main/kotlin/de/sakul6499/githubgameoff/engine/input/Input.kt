package de.sakul6499.githubgameoff.engine.input

import com.studiohartman.jamepad.ControllerIndex
import com.studiohartman.jamepad.ControllerManager
import com.studiohartman.jamepad.ControllerUnpluggedException
import de.sakul6499.githubgameoff.engine.Engine
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

/**
 * 1. Controller Axis
 * 2. Controller Buttons
 * 3. Keyboard
 */
object Input : KeyListener {
    private var inputConfigurations: InputConfig = Engine.GetJSONConfigOrCreate("Input", InputConfig::class.java, InputConfig())

    private val controllerManager: ControllerManager = ControllerManager()

    private val keyboardMap: HashMap<Int, Boolean> = hashMapOf()

    init {
        println("Launching SDL GamePad Library ...")
        controllerManager.initSDLGamepad()

        if (inputConfigurations.moveUsesKeyboard()) {
            inputConfigurations.MOVE_UP.keys.forEach { keyboardMap.put(it.keyCode, false) }
            inputConfigurations.MOVE_DOWN.keys.forEach { keyboardMap.put(it.keyCode, false) }
            inputConfigurations.MOVE_LEFT.keys.forEach { keyboardMap.put(it.keyCode, false) }
            inputConfigurations.MOVE_RIGHT.keys.forEach { keyboardMap.put(it.keyCode, false) }
        }

        if (inputConfigurations.fireUsesKeyboard()) {
            inputConfigurations.FIRE_UP.keys.forEach { keyboardMap.put(it.keyCode, false) }
            inputConfigurations.FIRE_DOWN.keys.forEach { keyboardMap.put(it.keyCode, false) }
            inputConfigurations.FIRE_LEFT.keys.forEach { keyboardMap.put(it.keyCode, false) }
            inputConfigurations.FIRE_RIGHT.keys.forEach { keyboardMap.put(it.keyCode, false) }
        }

        if (inputConfigurations.MENU.usesKeyboard()) {
            inputConfigurations.MENU.keys.forEach { keyboardMap.put(it.keyCode, false) }
        }
    }

    fun updateMovement(): Vector2F {
        val result = Vector2F()

        if (inputConfigurations.moveUsesControllerAxis()) {
            controllerManager.update()
            if (controllerManager.numControllers > 0) {
                for (i in 0..controllerManager.numControllers) {
                    try {
                        val controllerIndex: ControllerIndex = controllerManager.getControllerIndex(i)
                        if (controllerIndex.isConnected) {
                            if (inputConfigurations.MOVE_UP.usesControllerAxis()) {
                                inputConfigurations.MOVE_UP.controllerAxises.forEach {
                                    val value = controllerIndex.getAxisState(it.controllerAxis)
                                    if ((it.axisData == InputControllerAxis.AxisData.POSITIVE && value > 0) || (it.axisData == InputControllerAxis.AxisData.NEGATIVE && value < 0)) result.y = -value
                                }
                            }

                            if (inputConfigurations.MOVE_DOWN.usesControllerAxis()) {
                                inputConfigurations.MOVE_DOWN.controllerAxises.forEach {
                                    val value = controllerIndex.getAxisState(it.controllerAxis)
                                    if ((it.axisData == InputControllerAxis.AxisData.POSITIVE && value > 0) || (it.axisData == InputControllerAxis.AxisData.NEGATIVE && value < 0)) result.y = -value
                                }
                            }

                            if (inputConfigurations.MOVE_LEFT.usesControllerAxis()) {
                                inputConfigurations.MOVE_LEFT.controllerAxises.forEach {
                                    val value = controllerIndex.getAxisState(it.controllerAxis)
                                    if ((it.axisData == InputControllerAxis.AxisData.POSITIVE && value > 0) || (it.axisData == InputControllerAxis.AxisData.NEGATIVE && value < 0)) result.x = value
                                }
                            }

                            if (inputConfigurations.MOVE_RIGHT.usesControllerAxis()) {
                                inputConfigurations.MOVE_RIGHT.controllerAxises.forEach {
                                    val value = controllerIndex.getAxisState(it.controllerAxis)
                                    if ((it.axisData == InputControllerAxis.AxisData.POSITIVE && value > 0) || (it.axisData == InputControllerAxis.AxisData.NEGATIVE && value < 0)) result.x = value
                                }
                            }
                        }
                    } catch (e: ControllerUnpluggedException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        if (result.isNull() && inputConfigurations.moveUsesControllerButtons()) {
            controllerManager.update()
            if (controllerManager.numControllers > 0) {
                for (i in 0..controllerManager.numControllers) {
                    try {
                        val controllerIndex: ControllerIndex = controllerManager.getControllerIndex(i)
                        if (controllerIndex.isConnected) {
                            if (inputConfigurations.MOVE_UP.usesControllerButtons()) {
                                inputConfigurations.MOVE_UP.controllerButtons.forEach {
                                    if (controllerIndex.isButtonPressed(it.controllerKey)) {
                                        result.y = -1F
                                    }
                                }
                            }

                            if (inputConfigurations.MOVE_DOWN.usesControllerButtons()) {
                                inputConfigurations.MOVE_DOWN.controllerButtons.forEach {
                                    if (controllerIndex.isButtonPressed(it.controllerKey)) {
                                        result.y = 1F
                                    }
                                }
                            }

                            if (inputConfigurations.MOVE_LEFT.usesControllerButtons()) {
                                inputConfigurations.MOVE_LEFT.controllerButtons.forEach {
                                    if (controllerIndex.isButtonPressed(it.controllerKey)) {
                                        result.x = -1F
                                    }
                                }
                            }

                            if (inputConfigurations.MOVE_RIGHT.usesControllerButtons()) {
                                inputConfigurations.MOVE_RIGHT.controllerButtons.forEach {
                                    if (controllerIndex.isButtonPressed(it.controllerKey)) {
                                        result.x = 1F
                                    }
                                }
                            }
                        }
                    } catch (e: ControllerUnpluggedException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        if (result.isNull() && inputConfigurations.moveUsesKeyboard()) {
            if (inputConfigurations.MOVE_UP.keys.any { keyboardMap.get(it.keyCode)!! }) result.y = -1F
            if (inputConfigurations.MOVE_DOWN.keys.any { keyboardMap.get(it.keyCode)!! }) result.y = 1F
            if (inputConfigurations.MOVE_LEFT.keys.any { keyboardMap.get(it.keyCode)!! }) result.x = -1F
            if (inputConfigurations.MOVE_RIGHT.keys.any { keyboardMap.get(it.keyCode)!! }) result.x = 1F
        }

        return result
    }

    fun updateFire(): Vector2F {
        val result = Vector2F()

        if (inputConfigurations.fireUsesControllerAxis()) {
            controllerManager.update()
            if (controllerManager.numControllers > 0) {
                for (i in 0..controllerManager.numControllers) {
                    try {
                        val controllerIndex: ControllerIndex = controllerManager.getControllerIndex(i)
                        if (controllerIndex.isConnected) {
                            if (inputConfigurations.FIRE_UP.usesControllerAxis()) {
                                inputConfigurations.FIRE_UP.controllerAxises.forEach {
                                    val value = controllerIndex.getAxisState(it.controllerAxis)
                                    if ((it.axisData == InputControllerAxis.AxisData.POSITIVE && value > 0) || (it.axisData == InputControllerAxis.AxisData.NEGATIVE && value < 0)) result.y = -value
                                }
                            }

                            if (inputConfigurations.FIRE_DOWN.usesControllerAxis()) {
                                inputConfigurations.FIRE_DOWN.controllerAxises.forEach {
                                    val value = controllerIndex.getAxisState(it.controllerAxis)
                                    if ((it.axisData == InputControllerAxis.AxisData.POSITIVE && value > 0) || (it.axisData == InputControllerAxis.AxisData.NEGATIVE && value < 0)) result.y = -value
                                }
                            }

                            if (inputConfigurations.FIRE_LEFT.usesControllerAxis()) {
                                inputConfigurations.FIRE_LEFT.controllerAxises.forEach {
                                    val value = controllerIndex.getAxisState(it.controllerAxis)
                                    if ((it.axisData == InputControllerAxis.AxisData.POSITIVE && value > 0) || (it.axisData == InputControllerAxis.AxisData.NEGATIVE && value < 0)) result.x = value
                                }
                            }

                            if (inputConfigurations.FIRE_RIGHT.usesControllerAxis()) {
                                inputConfigurations.FIRE_RIGHT.controllerAxises.forEach {
                                    val value = controllerIndex.getAxisState(it.controllerAxis)
                                    if ((it.axisData == InputControllerAxis.AxisData.POSITIVE && value > 0) || (it.axisData == InputControllerAxis.AxisData.NEGATIVE && value < 0)) result.x = value
                                }
                            }
                        }
                    } catch (e: ControllerUnpluggedException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        if (result.isNull() && inputConfigurations.fireUsesControllerButtons()) {
            controllerManager.update()
            if (controllerManager.numControllers > 0) {
                for (i in 0..controllerManager.numControllers) {
                    try {
                        val controllerIndex: ControllerIndex = controllerManager.getControllerIndex(i)
                        if (controllerIndex.isConnected) {
                            if (inputConfigurations.FIRE_UP.usesControllerButtons()) {
                                inputConfigurations.FIRE_UP.controllerButtons.forEach {
                                    if (controllerIndex.isButtonPressed(it.controllerKey)) {
                                        result.y = -1F
                                    }
                                }
                            }

                            if (inputConfigurations.FIRE_DOWN.usesControllerButtons()) {
                                inputConfigurations.FIRE_DOWN.controllerButtons.forEach {
                                    if (controllerIndex.isButtonPressed(it.controllerKey)) {
                                        result.y = 1F
                                    }
                                }
                            }

                            if (inputConfigurations.FIRE_LEFT.usesControllerButtons()) {
                                inputConfigurations.FIRE_LEFT.controllerButtons.forEach {
                                    if (controllerIndex.isButtonPressed(it.controllerKey)) {
                                        result.x = -1F
                                    }
                                }
                            }

                            if (inputConfigurations.FIRE_RIGHT.usesControllerButtons()) {
                                inputConfigurations.FIRE_RIGHT.controllerButtons.forEach {
                                    if (controllerIndex.isButtonPressed(it.controllerKey)) {
                                        result.x = 1F
                                    }
                                }
                            }
                        }
                    } catch (e: ControllerUnpluggedException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        if (result.isNull() && inputConfigurations.fireUsesKeyboard()) {
            if (inputConfigurations.FIRE_UP.keys.any { keyboardMap.get(it.keyCode)!! }) result.y = -1F
            if (inputConfigurations.FIRE_DOWN.keys.any { keyboardMap.get(it.keyCode)!! }) result.y = 1F
            if (inputConfigurations.FIRE_LEFT.keys.any { keyboardMap.get(it.keyCode)!! }) result.x = -1F
            if (inputConfigurations.FIRE_RIGHT.keys.any { keyboardMap.get(it.keyCode)!! }) result.x = 1F
        }

        return result
    }

    override fun keyTyped(event: KeyEvent) = Unit

    override fun keyPressed(event: KeyEvent) {
        if (keyboardMap.containsKey(event.keyCode)) keyboardMap[event.keyCode] = true
    }

    override fun keyReleased(event: KeyEvent) {
        if (keyboardMap.containsKey(event.keyCode)) keyboardMap[event.keyCode] = false
    }

    fun exit() {
        println("Freeing SDL GamePad library ...")
        controllerManager.quitSDLGamepad()

        System.runFinalization()
    }
}