package de.sakul6499.githubgameoff.engine.input

import java.util.*

data class InputData(val keys: Array<InputKey> = arrayOf(), val controllerButtons: Array<InputControllerKey> = arrayOf(), val controllerAxises: Array<InputControllerAxis> = arrayOf()) {
    fun usesControllerAxis(): Boolean = controllerAxises.isNotEmpty()
    fun usesControllerButtons(): Boolean = controllerButtons.isNotEmpty()
    fun usesKeyboard(): Boolean = keys.isNotEmpty()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InputData

        if (!Arrays.equals(keys, other.keys)) return false
        if (!Arrays.equals(controllerButtons, other.controllerButtons)) return false
        if (!Arrays.equals(controllerAxises, other.controllerAxises)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(keys)
        result = 31 * result + Arrays.hashCode(controllerButtons)
        result = 31 * result + Arrays.hashCode(controllerAxises)
        return result
    }

    override fun toString(): String =
            "InputData(keys=${Arrays.toString(keys)}, controllerButtons=${Arrays.toString(controllerButtons)}, controllerAxises=${Arrays.toString(controllerAxises)})"
}