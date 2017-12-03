package de.sakul6499.githubgameoff.engine

import com.google.gson.Gson
import de.sakul6499.githubgameoff.engine.graphics.asset.SpriteFont
import org.lwjgl.glfw.GLFWErrorCallback
import java.awt.Color
import java.awt.Graphics
import java.awt.Image
import java.io.File

object Engine {
    val gameName: String = "GitHubGameOff"

    /* Config */

    fun getConfigDirectory(): File {
        val systemName = System.getProperty("os.name").toLowerCase()
        val dir = when {
            systemName.indexOf("win") >= 0 -> // On windows use AppData
                File("${System.getenv("AppData")}/.$gameName")
            systemName.indexOf("mac") >= 0 -> File("${System.getProperty("user.home")}/Library/Application Support/.$gameName")
            else -> // Linux
                File("${System.getProperty("user.home")}/.$gameName")
        }

        if (!dir.exists()) {
            println("Creating config directory: $dir")
            dir.mkdirs()
        }

        return dir
    }

    fun getConfigFile(name: String): File = File(getConfigDirectory(), name)

    fun getJSONConfigFile(name: String): File = File(getConfigDirectory(), "$name.json")

    fun <T : Class<*>> getJSONConfig(name: String, type: T): T = Gson().fromJson<T>((if (name.endsWith(".json") || name.endsWith(".config")) getConfigFile(name) else getJSONConfigFile(name)).readText(), type)

    fun <T : Any> getJSONConfigOrCreate(name: String, type: Class<T>, default: T? = null): T {
        val file = if (name.endsWith(".json") || name.endsWith(".config")) getConfigFile(name) else getJSONConfigFile(name)
        println(file)
        if (!file.exists()) {
            println("Config '$file' does not exists!")
            println("Creating config '$file' ...")

            file.createNewFile()
            if (default == null) {
                file.writeText("{}")
            } else {
                file.writeText(Gson().toJson(default))
            }
        }

        @Suppress("UNCHECKED_CAST")
        return Gson().fromJson(file.readText(), type) as T
    }

    /* Render */

    fun renderRectangle(graphics: Graphics, x: Int, y: Int, width: Int, height: Int, color: Color) {
        graphics.color = color
        graphics.fillRect(x, y, width, height)
    }

    fun renderText(graphics: Graphics, text: String, x: Int, y: Int, centered: Boolean, fontColor: SpriteFont.FontColor, fontType: SpriteFont.FontType, fontWidth: Int, fontHeight: Int, fontVerticalSpacing: Int) {
        var charIndex = 0
        var lineIndex = 0

        val lines: List<String> = if (text.contains('\n')) {
            text.split('\n')
        } else {
            listOf(text)
        }

        if (centered) {
            lines.forEach { line ->
                // Note: no | scaling
                val startX = x - SpriteFont.getStringLength(line, fontWidth, fontVerticalSpacing) / 2
                val startY = y - fontHeight

                SpriteFont.getString(line, fontType, fontColor).forEach {
                    graphics.drawImage(it, startX + (charIndex++ * fontVerticalSpacing), startY + (fontHeight * lineIndex), fontWidth, fontHeight, null)
                }

                lineIndex++
                charIndex = 0
            }
        } else {
            lines.forEach { line ->
                SpriteFont.getString(line, fontType, fontColor).forEach {
                    graphics.drawImage(it, x + (charIndex++ * fontVerticalSpacing), y + (fontHeight * lineIndex), fontWidth, fontHeight, null)
                }

                lineIndex++
                charIndex = 0
            }
        }
    }

    fun renderImage(graphics: Graphics, image: Image, x: Int, y: Int, width: Int, height: Int) {
        graphics.drawImage(image, x, y, width, height, null)
    }

    fun renderBox(graphics: Graphics, x: Int, y: Int, width: Int, height: Int, color: Color = Color.RED) {
        graphics.color = color
        graphics.drawRect(x, y, width, height)
    }
}