package de.sakul6499.githubgameoff.engine.graphics

import de.sakul6499.githubgameoff.engine.maths.Vector2I

abstract class Layer(name: String, order: Int, v0: Vector2I = Vector2I(), v1: Vector2I = Vector2I(Screen.GetWidth(), Screen.GetHeight())) : BasicLayer(name, order, v0, v1), Updateable, Renderable {
    override fun toString(): String = "Layer: $name [active: $isActive]"
}