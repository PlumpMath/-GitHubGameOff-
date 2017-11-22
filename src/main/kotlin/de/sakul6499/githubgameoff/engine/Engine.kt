package de.sakul6499.githubgameoff.engine

import com.google.gson.Gson
import java.io.File

object Engine {
    val GameName: String = "GitHubGameOff"

    fun GetConfigDirectory(): File {
        val systemName = System.getProperty("os.name").toLowerCase()
        val dir = when {
            systemName.indexOf("win") >= 0 -> // On windows use AppData
                File("${System.getenv("AppData")}/.$GameName")
            systemName.indexOf("mac") >= 0 -> File("${System.getProperty("user.home")}/Library/Application Support/.$GameName")
            else -> // Linux
                File("${System.getProperty("user.home")}/.$GameName")
        }

        if (!dir.exists()) {
            println("Creating config directory: $dir")
            dir.mkdirs()
        }

        return dir
    }

    fun GetConfigFile(name: String): File = File(GetConfigDirectory(), name)

    fun GetJSONConfigFile(name: String): File = File(GetConfigDirectory(), "$name.json")

    fun <T : Class<*>> GetJSONConfig(name: String, type: T): T = Gson().fromJson<T>((if (name.endsWith(".json") || name.endsWith(".config")) GetConfigFile(name) else GetJSONConfigFile(name)).readText(), type)

    fun <T : Any> GetJSONConfigOrCreate(name: String, type: Class<T>, default: T? = null): T {
        val file = if (name.endsWith(".json") || name.endsWith(".config")) GetConfigFile(name) else GetJSONConfigFile(name)
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

    // TODO: Garbager
}