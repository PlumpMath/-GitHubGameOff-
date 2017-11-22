package de.sakul6499.githubgameoff.engine.maths

class Vector2F(override var x: Float = 0.0F, override var y: Float = 0.0F) : Vector<Float> {
    constructor(other: Vector2F) : this(other.x, other.y)
    constructor(other: Vector2I) : this(other.x.toFloat(), other.y.toFloat())
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())

    override fun add(x0: Float, y0: Float): Vector2F {
        this.x += x0
        this.y += y0

        return this
    }

    override fun subtract(x0: Float, y0: Float): Vector2F {
        this.x -= x0
        this.y -= y0

        return this
    }

    override fun multiply(x0: Float, y0: Float): Vector2F {
        this.x *= x0
        this.y *= y0

        return this
    }

    override fun divide(x0: Float, y0: Float): Vector2F {
        this.x /= x0
        this.y /= y0

        return this
    }

    override fun normalize(): Vector2F {
        val length = Math.sqrt((x * x + y * y).toDouble()).toFloat()
        if (length != 0.0F) {
            multiply(1.0F / length, 1.0F / length)
        }

        return this
    }

    override fun distance(x0: Float, y0: Float): Double = Math.sqrt(((this.x - x0) * (this.x - x0) + (this.y - y0) * (this.y - y0)).toDouble())

    override fun diff(x0: Float, y0: Float): Vector2F = Vector2F(this.x - x0, this.y - y0)

    override fun nullify(): Vector2F {
        this.x = 0.0F
        this.y = 0.0F

        return this
    }

    override fun isNull(): Boolean = this.x == 0.0F && this.y == 0.0F

    override fun copy(): Vector2F = Vector2F(x, y)

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
}