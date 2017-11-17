package de.sakul6499.githubgameoff.engine.maths

class Vector2F(var x: Float = 0.0F, var y: Float = 0.0F) {
    constructor(other: Vector2F) : this(other.x, other.y)
    constructor(other: Vector2I) : this(other.x.toFloat(), other.y.toFloat())

    fun add(x: Float, y: Float): Vector2F {
        this.x += x
        this.y += y

        return this
    }

    fun add(other: Vector2F): Vector2F = add(other.x, other.y)
    fun add(other: Vector2I): Vector2F = add(other.x.toFloat(), other.y.toFloat())

    fun substract(x: Float, y: Float): Vector2F {
        this.x -= x
        this.y -= y

        return this
    }
    fun substract(other: Vector2F): Vector2F = substract(other.x, other.y)
    fun substract(other: Vector2I): Vector2F = substract(other.x.toFloat(), other.y.toFloat())

    fun multiply(x: Float, y: Float): Vector2F {
        this.x *= x
        this.y *= y

        return this
    }
    fun multiply(other: Vector2F): Vector2F = multiply(other.x, other.y)
    fun multiply(other: Vector2I): Vector2F = multiply(other.x.toFloat(), other.y.toFloat())

    fun divide(x: Float, y: Float): Vector2F {
        this.x /= x
        this.y /= y

        return this
    }
    fun divide(other: Vector2F): Vector2F = divide(other.x, other.y)
    fun divide(other: Vector2I): Vector2F = divide(other.x.toFloat(), other.y.toFloat())

    fun normalize(): Vector2F {
        val length = Math.sqrt((x * x + y * y).toDouble()).toFloat()
        if (length != 0.0F) {
            multiply(1.0F / length, 1.0F / length)
        }

        return this
    }

    fun distance(x: Float, y: Float): Double = Math.sqrt(((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)).toDouble())
    fun distance(other: Vector2F): Double = distance(other.x, other.y)
    fun distance(other: Vector2I): Double = distance(other.x.toFloat(), other.y.toFloat())

    fun nullify(): Vector2F {
        this.x = 0.0F
        this.y = 0.0F

        return this
    }

    fun isNull(): Boolean = this.x == 0.0F && this.y == 0.0F

    fun copy(): Vector2F = Vector2F(x, y)

    fun getRoundX(): Int = Math.round(x)
    fun getRoundY(): Int = Math.round(y)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector2F

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    override fun toString(): String = "Vector2F(x=$x, y=$y)"

    fun toVector2I(): Vector2I = Vector2I(x.toInt(), y.toInt())
}