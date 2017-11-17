package de.sakul6499.githubgameoff.engine

import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.engine.maths.Vector2I

open class PositionTranslationException: Exception {
    constructor(x: Float, y: Float): super("Could not translate position x=$x, y=$y")
    constructor(vector: Vector2F): this(vector.x, vector.y)

    constructor(x: Int, y: Int): super("Could not translate position x=$x, y=$y")
    constructor(vector: Vector2I): this(vector.x, vector.y)

    constructor(x: Float, y: Float, xValidRangeBegin: Float, xValidRangeEnd: Float, yValidRangeBegin: Float, yValidRangeEnd: Float): super("Could not translate position x=$x, y=$y. Valid Range X $xValidRangeBegin - $xValidRangeEnd, Y $yValidRangeBegin - $yValidRangeEnd!")
    constructor(vector: Vector2F, xValidRangeBegin: Float, xValidRangeEnd: Float, yValidRangeBegin: Float, yValidRangeEnd: Float): this(vector.x, vector.y, xValidRangeBegin, xValidRangeEnd, yValidRangeBegin, yValidRangeEnd)

    constructor(x: Int, y: Int, xValidRangeBegin: Int, xValidRangeEnd: Int, yValidRangeBegin: Int, yValidRangeEnd: Int): super("Could not translate position x=$x, y=$y. Valid Range X $xValidRangeBegin - $xValidRangeEnd, Y $yValidRangeBegin - $yValidRangeEnd!")
    constructor(vector: Vector2I, xValidRangeBegin: Int, xValidRangeEnd: Int, yValidRangeBegin: Int, yValidRangeEnd: Int): this(vector.x, vector.y, xValidRangeBegin, xValidRangeEnd, yValidRangeBegin, yValidRangeEnd)

    constructor(msg: String, cause: Throwable): super(msg, cause)
}