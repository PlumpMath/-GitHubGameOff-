package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.graphics.Updateable
import de.sakul6499.githubgameoff.engine.maths.Vector
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.engine.maths.VectorBox

class Enemy(val position: Vector2F, private val size: Int) : Updateable {

    var boxVector: VectorBox<Vector<Float>> = VectorBox(position.copy().subtract(size.toFloat() / 2), position.copy().add(size.toFloat() / 2))
        private set
        get() {
            field = VectorBox(position.copy().subtract(size.toFloat() / 2), position.copy().add(size.toFloat() / 2))
            return field
        }

    override fun update(delta: Long, alpha: Long) {
        position.add(PlayerLayer.position.diff(position).divide(218F))
    }
}