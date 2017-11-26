package de.sakul6499.githubgameoff.engine.graphics

import de.sakul6499.githubgameoff.engine.maths.BoxVector
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.engine.maths.Vector2I

open class PositionTranslationException : Exception {
    constructor(x: Float, y: Float) : super("Could not translate position x=$x, y=$y")
    constructor(vector: Vector2F) : this(vector.x, vector.y)

    constructor(x: Int, y: Int) : super("Could not translate position x=$x, y=$y")
    constructor(vector: Vector2I) : this(vector.x, vector.y)

    constructor(x: Float, y: Float, xValidRangeBegin: Float, xValidRangeEnd: Float, yValidRangeBegin: Float, yValidRangeEnd: Float) : super("Could not translate position x=$x, y=$y. Valid Range X $xValidRangeBegin - $xValidRangeEnd, Y $yValidRangeBegin - $yValidRangeEnd!")
    constructor(vector: Vector2F, xValidRangeBegin: Float, xValidRangeEnd: Float, yValidRangeBegin: Float, yValidRangeEnd: Float) : this(vector.x, vector.y, xValidRangeBegin, xValidRangeEnd, yValidRangeBegin, yValidRangeEnd)

    constructor(x: Int, y: Int, xValidRangeBegin: Int, xValidRangeEnd: Int, yValidRangeBegin: Int, yValidRangeEnd: Int) : super("Could not translate position x=$x, y=$y. Valid Range X $xValidRangeBegin - $xValidRangeEnd, Y $yValidRangeBegin - $yValidRangeEnd!")
    constructor(vector: Vector2I, xValidRangeBegin: Int, xValidRangeEnd: Int, yValidRangeBegin: Int, yValidRangeEnd: Int) : this(vector.x, vector.y, xValidRangeBegin, xValidRangeEnd, yValidRangeBegin, yValidRangeEnd)

    constructor(v: Vector2I, b: BoxVector<Vector2I>) : super("Could not translate $v. Valid box: $b")
    constructor(v: Vector2F, b: BoxVector<Vector2F>) : super("Could not translate $v. Valid box: $b")

    constructor(b0: BoxVector<*>, b1: BoxVector<*>, layer: BasicLayer? = null) : super("Could not translate $b0. Valid box: $b1, [$layer]")

    constructor(msg: String, cause: Throwable) : super(msg, cause)
}