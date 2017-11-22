package de.sakul6499.githubgameoff.engine.input

import com.studiohartman.jamepad.ControllerAxis
import com.studiohartman.jamepad.ControllerButton
import java.awt.event.KeyEvent

data class InputConfig(
        val MOVE_UP: InputData = InputData(arrayOf(InputKey(KeyEvent.VK_W)), arrayOf(InputControllerKey(ControllerButton.DPAD_UP)), arrayOf(InputControllerAxis(ControllerAxis.LEFTY, InputControllerAxis.AxisData.NEGATIVE))),
        val MOVE_DOWN: InputData = InputData(arrayOf(InputKey(KeyEvent.VK_S)), arrayOf(InputControllerKey(ControllerButton.DPAD_DOWN)), arrayOf(InputControllerAxis(ControllerAxis.LEFTY, InputControllerAxis.AxisData.POSITIVE))),
        val MOVE_LEFT: InputData = InputData(arrayOf(InputKey(KeyEvent.VK_A)), arrayOf(InputControllerKey(ControllerButton.DPAD_LEFT)), arrayOf(InputControllerAxis(ControllerAxis.LEFTX, InputControllerAxis.AxisData.NEGATIVE))),
        val MOVE_RIGHT: InputData = InputData(arrayOf(InputKey(KeyEvent.VK_D)), arrayOf(InputControllerKey(ControllerButton.DPAD_RIGHT)), arrayOf(InputControllerAxis(ControllerAxis.LEFTX, InputControllerAxis.AxisData.POSITIVE))),

        val FIRE_UP: InputData = InputData(arrayOf(InputKey(KeyEvent.VK_UP)), arrayOf(InputControllerKey(ControllerButton.Y)), arrayOf(InputControllerAxis(ControllerAxis.RIGHTY, InputControllerAxis.AxisData.NEGATIVE))),
        val FIRE_DOWN: InputData = InputData(arrayOf(InputKey(KeyEvent.VK_DOWN)), arrayOf(InputControllerKey(ControllerButton.A)), arrayOf(InputControllerAxis(ControllerAxis.RIGHTY, InputControllerAxis.AxisData.POSITIVE))),
        val FIRE_LEFT: InputData = InputData(arrayOf(InputKey(KeyEvent.VK_LEFT)), arrayOf(InputControllerKey(ControllerButton.X)), arrayOf(InputControllerAxis(ControllerAxis.RIGHTX, InputControllerAxis.AxisData.NEGATIVE))),
        val FIRE_RIGHT: InputData = InputData(arrayOf(InputKey(KeyEvent.VK_RIGHT)), arrayOf(InputControllerKey(ControllerButton.B)), arrayOf(InputControllerAxis(ControllerAxis.RIGHTX, InputControllerAxis.AxisData.POSITIVE))),

        val MENU: InputData = InputData(arrayOf(InputKey(KeyEvent.VK_ESCAPE)), arrayOf(InputControllerKey(ControllerButton.START)), arrayOf())
) {
    fun moveUsesControllerAxis(): Boolean = MOVE_UP.usesControllerAxis() || MOVE_DOWN.usesControllerAxis() || MOVE_LEFT.usesControllerAxis() || MOVE_RIGHT.usesControllerAxis()
    fun moveUsesControllerButtons(): Boolean = MOVE_UP.usesControllerButtons() || MOVE_DOWN.usesControllerButtons() || MOVE_LEFT.usesControllerButtons() || MOVE_RIGHT.usesControllerButtons()
    fun moveUsesKeyboard(): Boolean = MOVE_UP.usesKeyboard() || MOVE_DOWN.usesKeyboard() || MOVE_LEFT.usesKeyboard() || MOVE_RIGHT.usesKeyboard()

    fun fireUsesControllerAxis(): Boolean = FIRE_UP.usesControllerAxis() || FIRE_DOWN.usesControllerAxis() || FIRE_LEFT.usesControllerAxis() || FIRE_RIGHT.usesControllerAxis()
    fun fireUsesControllerButtons(): Boolean = FIRE_UP.usesControllerButtons() || FIRE_DOWN.usesControllerButtons() || FIRE_LEFT.usesControllerButtons() || FIRE_RIGHT.usesControllerButtons()
    fun fireUsesKeyboard(): Boolean = FIRE_UP.usesKeyboard() || FIRE_DOWN.usesKeyboard() || FIRE_LEFT.usesKeyboard() || FIRE_RIGHT.usesKeyboard()
}