package de.sakul6499.githubgameoff.engine.maths

class Vector2I(var x: Int = 0, var y: Int = 0) {
    constructor(other: Vector2I) : this(other.x, other.y)
    constructor(other: Vector2F) : this(other.x.toInt(), other.y.toInt())

    fun add(x: Int, y: Int): Vector2I {
        this.x += x
        this.y += y

        return this
    }

    fun add(other: Vector2I): Vector2I = add(other.x, other.y)
    fun add(other: Vector2F): Vector2I = add(other.x.toInt(), other.y.toInt())

    fun substract(x: Int, y: Int): Vector2I {
        this.x -= x
        this.y -= y

        return this
    }

    fun substract(other: Vector2I): Vector2I = substract(other.x, other.y)
    fun substract(other: Vector2F): Vector2I = substract(other.x.toInt(), other.y.toInt())

    fun multiply(x: Int, y: Int): Vector2I {
        this.x *= x
        this.y *= y

        return this
    }

    fun multiply(other: Vector2I): Vector2I = multiply(other.x, other.y)
    fun multiply(other: Vector2F): Vector2I = multiply(other.x.toInt(), other.y.toInt())

    fun divide(x: Int, y: Int): Vector2I {
        this.x /= x
        this.y /= y

        return this
    }

    fun divide(other: Vector2I): Vector2I = divide(other.x, other.y)
    fun divide(other: Vector2F): Vector2I = divide(other.x.toInt(), other.y.toInt())

    fun normalize(): Vector2I {
        val length = Math.sqrt((x * x + y * y).toDouble()).toInt()
        if (length != 0) {
            multiply(1 / length, 1 / length)
        }

        return this
    }

    fun distance(x: Int, y: Int): Double = Math.sqrt(((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)).toDouble())
    fun distance(other: Vector2I): Double = distance(other.x, other.y)
    fun distance(other: Vector2F): Double = distance(other.x.toInt(), other.y.toInt())

    fun nullify(): Vector2I {
        this.x = 0
        this.y = 0

        return this
    }

    fun isNull(): Boolean = this.x == 0 && this.y == 0

    fun copy(): Vector2I = Vector2I(x, y)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector2I

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    override fun toString(): String = "Vector2I(x=$x, y=$y)"

    fun toVector2F(): Vector2F = Vector2F(x.toFloat(), y.toFloat())
}