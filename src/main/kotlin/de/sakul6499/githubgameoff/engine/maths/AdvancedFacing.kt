package de.sakul6499.githubgameoff.engine.maths

import de.sakul6499.githubgameoff.engine.util.FakeEnum

class AdvancedFacing(val advancedValue: Byte = 0) : BasicFacing(0), FakeEnum<Byte> {
    companion object {
        val NULL: Byte = 0
        val A: Byte = 1
    }
}