package de.sakul6499.githubgameoff.engine.maths

interface Vector<T : Number> {
    var x: T
    var y: T

    fun set(x0: T, y0: T): Vector<T> {
        this.x = x0
        this.y = y0

        return this
    }

    fun set(v0: Vector<T>): Vector<T> {
        this.x = v0.x
        this.y = v0.y

        return this
    }

    fun add(t0: T): Vector<T> = add(t0, t0)
    fun add(x0: T, y0: T): Vector<T>
    fun add(v0: Vector<T>): Vector<T> = add(v0.x, v0.y)

    fun subtract(t0: T): Vector<T> = subtract(t0, t0)
    fun subtract(x0: T, y0: T): Vector<T>
    fun subtract(v0: Vector<T>): Vector<T> = subtract(v0.x, v0.y)

    fun multiply(t0: T): Vector<T> = multiply(t0, t0)
    fun multiply(x0: T, y0: T): Vector<T>
    fun multiply(v0: Vector<T>): Vector<T> = multiply(v0.x, v0.y)

    fun divide(t0: T): Vector<T> = divide(t0, t0)
    fun divide(x0: T, y0: T): Vector<T>
    fun divide(v0: Vector<T>): Vector<T> = divide(v0.x, v0.y)

    fun normalize(): Vector<T>

    fun distance(x0: T, y0: T): Double
    fun distance(v0: Vector<T>): Double = distance(v0.x, v0.y)

    fun diff(x0: T, y0: T): Vector<T>
    fun diff(v0: Vector<T>): Vector<T> = diff(v0.x, v0.y)

    fun nullify(): Vector<T>

    fun isNull(): Boolean
    fun isNotNull(): Boolean = !isNull()

    fun copy(): Vector<T>

    fun equals(v0: Vector<T>): Boolean = this.x == v0.x && this.y == v0.y
}