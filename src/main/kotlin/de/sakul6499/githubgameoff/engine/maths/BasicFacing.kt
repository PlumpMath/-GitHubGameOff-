package de.sakul6499.githubgameoff.engine.maths

import de.sakul6499.githubgameoff.engine.util.FakeEnum
import kotlin.reflect.KProperty

open class BasicFacing(val value: Byte = BasicFacing.NULL) : FakeEnum<Byte> {
    companion object {
        val NULL: Byte = 0

        val UP: Byte = 1
        val DOWN: Byte = 2

        val LEFT: Byte = 3
        val RIGHT: Byte = 4
    }

    override fun values(): Array<Byte> = arrayOf(UP, DOWN, LEFT, RIGHT)

    override fun valueOf(name: String): Byte? {
        val member = Companion::class.members.filter { it.returnType.classifier == Byte::class && it is KProperty }.find { it.name == name } ?: return null
        @Suppress("UNCHECKED_CAST")
        return (member as KProperty<Byte>).getter.call(this)
    }
}