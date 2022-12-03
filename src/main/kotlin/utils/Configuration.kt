package utils

import Hotkey
import Link
import com.google.gson.GsonBuilder
import java.io.File

object Configuration {

    /**
     * Variable to access configuration values
     */
    lateinit var config: Config

    /**
     * Configuration [File]
     */
    private val file = File("config.json")

    /**
     * Gson Instance
     */
    private val gson = GsonBuilder().setPrettyPrinting().create()

    /**
     * Method to initialize the configuration - create file (if necessary) and read values
     */
    fun initialize() {
        Logger.debug("Initializing configuration...")

        if (!file.exists() || (file.readText() === "")) {
            Logger.warn("Config file does not exist/is empty!")
            createFileFromDefault()
        }

        readConfig()
    }

    /**
     * Method to create the default configuration file
     */
    private fun createFileFromDefault() {
        Logger.warn("Creating config file from default...")

        val exampleLink = Link(0, 1, Hotkey(123, true, true, true), 123, 456, 789)
        val defaultConfig = Config(false, false, true, listOf(exampleLink))
        val jsonString = gson.toJson(defaultConfig)

        file.writeText(jsonString)
    }

    /**
     * Method to read configuration file - save in [config]
     */
    private fun readConfig() {
        Logger.debug("Reading configuration...")

        try {
            config = gson.fromJson(file.readText(), Config::class.java)
            Logger.info("Configuration successfully read!")
        } catch (e: Exception) {
            Logger.error("Error while reading configuration (${e.message})")
        }

    }

}