package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.maths.Vector2I
import java.awt.Color
import java.awt.Graphics
import java.awt.Image

fun Graphics.drawImage(image: Image, x: Int, y: Int, width: Int, height: Int): Boolean = this.drawImage(image, x, y, width, height, null)

fun Graphics.drawImage(image: Image, position: Vector2I, size: Vector2I): Boolean = this.drawImage(image, position.x, position.y, size.x, size.y, null)

fun Graphics.renderImage(image: Image, position: Vector2I, size: Int): Boolean = this.drawImage(image, position.x - size / 2, position.y - size / 2, size, size)

fun Graphics.renderBox(x: Int, y: Int, width: Int, height: Int, color: Color = Color.MAGENTA) {
    this.color = color
    this.fillRect(x, y, width, height)
}

fun Graphics.renderBox(start: Vector2I, end: Vector2I, color: Color = Color.MAGENTA) {
    this.color = color
    this.fillRect(start.x, start.y, end.x - start.x, end.y - start.y)
}