package de.sakul6499.githubgameoff.engine.asset

import java.awt.image.BufferedImage
import javax.imageio.ImageIO


object SpriteFont {
    // the total sprite width and height
    private val spriteWidth = 2048
    private val spriteHeight = 768

    // each tile is a 128px per 128px image
    val tileDimension = 128

    // the rows [y] and entries per row [x] in the sprite
    private val rows = 6
    private val entries = 16

    // the chars in order
    private val font: Map<Pair<Int, Int>, Char> = mapOf(
            // First row
            Pair(Pair(0, 0), ' '),
            Pair(Pair(0, 1), '!'),
            Pair(Pair(0, 2), '"'),
            Pair(Pair(0, 3), '#'),
            Pair(Pair(0, 4), '$'),
            Pair(Pair(0, 5), '%'),
            Pair(Pair(0, 6), '&'),
            Pair(Pair(0, 7), '\''),
            Pair(Pair(0, 8), '('),
            Pair(Pair(0, 9), ')'),
            Pair(Pair(0, 10), '*'),
            Pair(Pair(0, 11), '+'),
            Pair(Pair(0, 12), ','),
            Pair(Pair(0, 13), '-'),
            Pair(Pair(0, 14), '.'),
            Pair(Pair(0, 15), '/'),

            // Second row
            Pair(Pair(1, 0), '0'),
            Pair(Pair(1, 1), '1'),
            Pair(Pair(1, 2), '2'),
            Pair(Pair(1, 3), '3'),
            Pair(Pair(1, 4), '4'),
            Pair(Pair(1, 5), '5'),
            Pair(Pair(1, 6), '6'),
            Pair(Pair(1, 7), '7'),
            Pair(Pair(1, 8), '8'),
            Pair(Pair(1, 9), '9'),
            Pair(Pair(1, 10), ':'),
            Pair(Pair(1, 11), ';'),
            Pair(Pair(1, 12), '<'),
            Pair(Pair(1, 13), '='),
            Pair(Pair(1, 14), '>'),
            Pair(Pair(1, 15), '?'),

            // Third row
            Pair(Pair(2, 0), '@'),
            Pair(Pair(2, 1), 'A'),
            Pair(Pair(2, 2), 'B'),
            Pair(Pair(2, 3), 'C'),
            Pair(Pair(2, 4), 'D'),
            Pair(Pair(2, 5), 'E'),
            Pair(Pair(2, 6), 'F'),
            Pair(Pair(2, 7), 'G'),
            Pair(Pair(2, 8), 'H'),
            Pair(Pair(2, 9), 'I'),
            Pair(Pair(2, 10), 'J'),
            Pair(Pair(2, 11), 'K'),
            Pair(Pair(2, 12), 'L'),
            Pair(Pair(2, 13), 'M'),
            Pair(Pair(2, 14), 'N'),
            Pair(Pair(2, 15), 'O'),

            // Fourth row
            Pair(Pair(3, 0), 'P'),
            Pair(Pair(3, 1), 'Q'),
            Pair(Pair(3, 2), 'R'),
            Pair(Pair(3, 3), 'S'),
            Pair(Pair(3, 4), 'T'),
            Pair(Pair(3, 5), 'U'),
            Pair(Pair(3, 6), 'V'),
            Pair(Pair(3, 7), 'W'),
            Pair(Pair(3, 8), 'X'),
            Pair(Pair(3, 9), 'Y'),
            Pair(Pair(3, 10), 'Z'),
            Pair(Pair(3, 11), '['),
            Pair(Pair(3, 12), '\\'),
            Pair(Pair(3, 13), ']'),
            Pair(Pair(3, 14), '^'),
            Pair(Pair(3, 15), '_'),

            // Fifth row
            Pair(Pair(4, 0), '`'),
            Pair(Pair(4, 1), 'a'),
            Pair(Pair(4, 2), 'b'),
            Pair(Pair(4, 3), 'c'),
            Pair(Pair(4, 4), 'd'),
            Pair(Pair(4, 5), 'e'),
            Pair(Pair(4, 6), 'f'),
            Pair(Pair(4, 7), 'g'),
            Pair(Pair(4, 8), 'h'),
            Pair(Pair(4, 9), 'i'),
            Pair(Pair(4, 10), 'j'),
            Pair(Pair(4, 11), 'k'),
            Pair(Pair(4, 12), 'l'),
            Pair(Pair(4, 13), 'm'),
            Pair(Pair(4, 14), 'n'),
            Pair(Pair(4, 15), 'o'),

            // Sixth row
            Pair(Pair(5, 0), 'p'),
            Pair(Pair(5, 1), 'q'),
            Pair(Pair(5, 2), 'r'),
            Pair(Pair(5, 3), 's'),
            Pair(Pair(5, 4), 't'),
            Pair(Pair(5, 5), 'u'),
            Pair(Pair(5, 6), 'v'),
            Pair(Pair(5, 7), 'w'),
            Pair(Pair(5, 8), 'x'),
            Pair(Pair(5, 9), 'y'),
            Pair(Pair(5, 10), 'z'),
            Pair(Pair(5, 11), '{'),
            Pair(Pair(5, 12), '|'),
            Pair(Pair(5, 13), '}'),
            Pair(Pair(5, 14), '~')
    )

    // the buffer with all tiles
    val buffer: MutableMap<String, BufferedImage> = mutableMapOf()

    fun getCharTileName(c: Char, fontType: FontType, color: FontColor): String {
        val pair = font.filterValues { it == c }.keys.firstOrNull() ?: throw IllegalStateException("Couldn't find '$c'!")
        return "${fontType}_${color}_${pair.first}_${pair.second}"
    }

    init {
        println("Loading font ...")

        for (type in FontType.values()) {
            for (color in FontColor.values()) {
                val fontSprite = ImageIO.read(this::class.java.getResource("/font/font_${type}_${color}.png"))

                for (y in 0 until rows) {
                    for (x in 0 until entries) {
                        buffer["${type}_${color}_${y}_${x}"] = fontSprite.getSubimage(x * tileDimension, y * tileDimension, tileDimension, tileDimension)
                    }
                }
            }
        }

        println("Finished loading font!")
    }

    enum class FontType {
        NORMAL,
        BOLD
    }

    enum class FontColor {
        BLACK,
        WHITE
    }

    fun getChar(c: Char, type: FontType = FontType.NORMAL, color: FontColor = FontColor.BLACK): BufferedImage = buffer[getCharTileName(c, type, color)] ?: throw IllegalStateException("Couldn't find font tile for '$c', '$type', '$color'!")

    fun getString(text: String, type: FontType = FontType.NORMAL, color: FontColor = FontColor.BLACK): Array<BufferedImage> {
        var result: Array<BufferedImage> = arrayOf()
        text.forEach { char -> result += getChar(char, type, color) }
        return result
    }

    fun getStringLength(text: String, fontWidth: Int = tileDimension, fontVerticalSpacing: Int = tileDimension): Int {
        if (fontWidth <= 0) throw IllegalArgumentException("fontWidth must not be smaller or equal null!")
        if (fontVerticalSpacing <= 0) throw IllegalArgumentException("fontVerticalSpacing must not be smaller or equal null!")
        if (fontWidth < fontVerticalSpacing) throw IllegalArgumentException("fontWidth must not be smaller than fontVerticalSpacing")

        return text.length * fontVerticalSpacing + (fontWidth - fontVerticalSpacing)
    }
}