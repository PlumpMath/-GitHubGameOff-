package de.sakul6499.githubgameoff.game.entity

import de.sakul6499.githubgameoff.engine.maths.Vector2I
import java.awt.Graphics
import java.awt.Image

fun Graphics.drawImage(image: Image, x: Int, y: Int, width: Int, height: Int): Boolean = this.drawImage(image, x, y, width, height, null)

fun Graphics.drawImage(image: Image, position: Vector2I, size: Vector2I): Boolean = this.drawImage(image, position.x, position.y, size.x, size.y, null)

fun Graphics.renderImage(image: Image, position: Vector2I, size: Int): Boolean = this.drawImage(image, position.x - size / 2, position.y - size / 2, size, size)