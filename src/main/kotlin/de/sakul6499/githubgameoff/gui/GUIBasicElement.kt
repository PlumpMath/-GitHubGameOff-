package de.sakul6499.githubgameoff.gui

abstract class GUIBasicElement(var x: Double, var y: Double, var sizeX: Double, var sizeY: Double) : GUIElement {
    override var active: Boolean = false

    fun makeActive() {
        active = true
    }

    fun makeInative() {
        active = false
    }

    fun contains(x: Double, y: Double): Boolean = this.x > x && this.x + sizeX < x && this.y > y && this.y + sizeY < y

    fun contains(x: Double, y: Double, w: Double, h: Double): Boolean = this.x > x && this.x + sizeX < x && this.y > y && this.y + sizeY < y &&
            this.x > x + w && this.x + sizeX < x + w && this.y > y && this.y + h + sizeY < y + h
}