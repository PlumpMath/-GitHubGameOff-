package de.sakul6499.githubgameoff.engine

import java.awt.Font
import java.awt.GraphicsEnvironment

data class GameConfig(
        val width: Int = 1280,
        val height: Int = 720,
        val deltaLimit: Int = 60,
        val fonts: List<Triple<String, Int, Int>> = listOf(
                Triple("Courier New", Font.PLAIN, 20),
                Triple("Courier", Font.PLAIN, 20),
                Triple("monospace", Font.PLAIN, 20)
        )
) {
    var validationResultErrors: Array<GameConfigValidateResult> = arrayOf()
        private set

    enum class GameConfigValidateResult(val text: String) {
        WIDTH_LOWER_OR_EQUAL_ZERO("The window width must not be lower or equal zero!"),
        HEIGHT_LOWER_OR_EQUAL_ZERO("The window height must not be lower or equal zero!"),
        UPS_LIMIT_LOWER_OR_EQUAL_ZERO("The UPS limit is lower or equal zero!")
    }

    fun validate(): Boolean {
        validationResultErrors = arrayOf()

        if (width < 0) validationResultErrors += GameConfigValidateResult.WIDTH_LOWER_OR_EQUAL_ZERO
        if (height < 0) validationResultErrors += GameConfigValidateResult.HEIGHT_LOWER_OR_EQUAL_ZERO
        if (deltaLimit < 0) validationResultErrors += GameConfigValidateResult.UPS_LIMIT_LOWER_OR_EQUAL_ZERO

        return validationResultErrors.isEmpty()
    }

    fun getValidFont(): Font = fonts.find { font -> GraphicsEnvironment.getLocalGraphicsEnvironment().availableFontFamilyNames.any { it == font.first } }.let { if (it == null) throw IllegalStateException("Couldn't found a valid found!") else Font(it.first, it.second, it.third) }
}