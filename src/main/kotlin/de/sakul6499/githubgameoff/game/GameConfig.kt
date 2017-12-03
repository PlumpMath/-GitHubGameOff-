package de.sakul6499.githubgameoff.game

data class GameConfig(
        val width: Int = 1280,
        val height: Int = 720,
        val deltaLimit: Int = 60
) {
    fun validate(): List<String> {
        val errors: MutableList<String> = mutableListOf()

        if (width < 0) errors += "The window width must not be lower or equal zero!"
        if (height < 0) errors += "The window height must not be lower or equal zero!"
        if (deltaLimit < 0) errors += "The UPS limit is lower or equal zero!"

        return errors
    }
}