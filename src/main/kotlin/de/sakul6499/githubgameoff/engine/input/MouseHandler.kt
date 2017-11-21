package de.sakul6499.githubgameoff.engine.input

import java.awt.Point
import java.awt.event.*

class MouseHandler private constructor() : MouseListener, MouseWheelListener, MouseMotionListener {

    companion object {
        val instance: MouseHandler = MouseHandler()

        var MousePosition: Point = Point()
            private set

        var MouseButtonLeftPressed: Boolean = false
            private set

        var MouseWheelPressed: Boolean = false
            private set

        var MouseButtonRightPressed: Boolean = false
            private set

        fun PressedAny(): Boolean = MouseButtonLeftPressed || MouseWheelPressed || MouseButtonRightPressed
    }

    override fun mousePressed(e: MouseEvent) {
        when (e.button) {
            MouseEvent.BUTTON1 -> MouseButtonLeftPressed = true
            MouseEvent.BUTTON2 -> MouseWheelPressed = true
            MouseEvent.BUTTON3 -> MouseButtonRightPressed = true
        }

        MousePosition = e.point
    }

    override fun mouseReleased(e: MouseEvent) {
        when (e.button) {
            MouseEvent.BUTTON1 -> MouseButtonLeftPressed = false
            MouseEvent.BUTTON2 -> MouseWheelPressed = false
            MouseEvent.BUTTON3 -> MouseButtonRightPressed = false
        }

        MousePosition = e.point
    }

    override fun mouseMoved(e: MouseEvent) {
        MousePosition = e.point
    }

    override fun mouseDragged(e: MouseEvent) {
        MousePosition = e.point
    }

    override fun mouseEntered(e: MouseEvent?) = Unit

    override fun mouseClicked(e: MouseEvent?) = Unit

    override fun mouseExited(e: MouseEvent?) = Unit

    override fun mouseWheelMoved(e: MouseWheelEvent?) = Unit
}