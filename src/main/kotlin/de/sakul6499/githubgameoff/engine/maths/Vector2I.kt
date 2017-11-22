package de.sakul6499.githubgameoff.engine.maths

class Vector2I(override var x: Int = 0, override var y: Int = 0) : Vector<Int> {
    constructor(other: Vector2I) : this(other.x, other.y)
    constructor(other: Vector2F) : this(other.x.toInt(), other.y.toInt())

    override fun add(x0: Int, y0: Int): Vector2I {
        this.x += x
        this.y += y

        return this
    }

    override fun subtract(x0: Int, y0: Int): Vector2I {
        this.x -= x
        this.y -= y

        return this
    }

    override fun multiply(x0: Int, y0: Int): Vector2I {
        this.x *= x
        this.y *= y

        return this
    }

    override fun divide(x0: Int, y0: Int): Vector2I {
        this.x /= x
        this.y /= y

        return this
    }

    override fun normalize(): Vector2I {
        val length = Math.sqrt((x * x + y * y).toDouble()).toInt()
        if (length != 0) {
            multiply(1 / length, 1 / length)
        }

        return this
    }

    override fun distance(x0: Int, y0: Int): Double = Math.sqrt(((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)).toDouble())

    override fun diff(x0: Int, y0: Int): Vector2I = Vector2I(this.x - x0, this.y - y0)

    override fun nullify(): Vector2I {
        this.x = 0
        this.y = 0

        return this
    }

    override fun isNull(): Boolean = this.x == 0 && this.y == 0

    override fun copy(): Vector2I = Vector2I(x, y)

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
}