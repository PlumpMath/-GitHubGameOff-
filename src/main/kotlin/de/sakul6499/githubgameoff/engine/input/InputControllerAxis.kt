package de.sakul6499.githubgameoff.engine.input

import com.studiohartman.jamepad.ControllerAxis

data class InputControllerAxis(val controllerAxis: ControllerAxis, val axisData: AxisData) {
    enum class AxisData {
        POSITIVE,
        NEGATIVE
    }
}