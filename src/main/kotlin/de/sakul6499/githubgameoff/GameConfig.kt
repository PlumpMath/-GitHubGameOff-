package de.sakul6499.githubgameoff

data class GameConfig(
        val width: Int = 1280,
        val height: Int = 720,
        val deltaLimit: Int = 60
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
}