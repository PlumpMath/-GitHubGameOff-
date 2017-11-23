package de.sakul6499.githubgameoff.engine.graphics

abstract class Layer(name: String, order: Int, x: Int = 0, y: Int = 0, width: Int = Screen.GetWidth(), height: Int = Screen.GetHeight()) : BasicLayer(name, order, x, y, width, height), Updateable, Renderable {
    override fun toString(): String = "Layer: $name [active: $isActive]"
}