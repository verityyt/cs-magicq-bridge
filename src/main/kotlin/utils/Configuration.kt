package utils

import com.google.gson.GsonBuilder
import java.io.File

object Configuration {

    lateinit var config: Config

    private val file = File("config.json")
    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun initialize() {
        Logger.debug("Initializing configuration...")

        if (!file.exists() || (file.readText() === "")) {
            Logger.warn("Config file does not exist/is empty!")
            createFileFromDefault()
        }

        readConfig()
    }

    private fun createFileFromDefault() {
        Logger.warn("Creating config file from default...")

        val defaultConfig = Config(false, false, true)
        val jsonString = gson.toJson(defaultConfig)

        file.writeText(jsonString)
    }

    private fun readConfig() {
        Logger.debug("Reading configuration...")

        try {
            config = gson.fromJson(file.readText(), Config::class.java)
            Logger.info("Configuration successfully read!")
        }catch (e: Exception) {
            Logger.error("Error while reading configuration (${e.message})")
        }

    }

}