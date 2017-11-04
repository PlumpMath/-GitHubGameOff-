package de.sakul6499.githubgameoff.game.gui

abstract class GUIBasicElement(override var x: Int, override var y: Int, var sizeX: Int, var sizeY: Int, override var active: Boolean = true) : GUIElement {
    fun contains(x: Int, y: Int): Boolean = this.x > x && this.x + sizeX < x && this.y > y && this.y + sizeY < y

    fun contains(x: Int, y: Int, w: Int, h: Int): Boolean = this.x > x && this.x + sizeX < x && this.y > y && this.y + sizeY < y &&
            this.x > x + w && this.x + sizeX < x + w && this.y > y && this.y + h + sizeY < y + h
}