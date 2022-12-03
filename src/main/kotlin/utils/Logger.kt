package utils

import java.text.SimpleDateFormat
import java.util.*

object Logger {

    /**
     * Whether debug logs should be displayed or not
     */
    private var enableDebug = true

    /**
     * Whether art-net logs should be displayed or not
     */
    private var enableArtNet = true

    /**
     * Whether logic logs should be displayed or not
     */
    private var enableLogic = true

    /**
     * Get current time in correct date format (HH:mm:ss)
     */
    private fun getFormattedTime() = SimpleDateFormat("HH:mm:ss").format(Date())

    private const val reset = "\u001B[0m"
    private const val grey = "\u001B[90m"
    private const val cyan = "\u001B[36m"
    private const val red = "\u001B[31m"
    private const val orange = "\u001B[33m"
    private const val green = "\u001B[32m"
    private const val purple = "\u001B[35m"
    private const val yellow = "\u001B[33m"


    /**
     * Sets logger settings from configuration
     */
    fun configure() {
        enableDebug = Configuration.config.enableDebugLogs
        enableArtNet = Configuration.config.enableArtNetLogs
        enableLogic = Configuration.config.enableLogicLogs
    }

    /**
     * Log a text as info log
     * @param msg The logging text
     */
    fun info(msg: String) {
        println("$grey[${getFormattedTime()}]: [${green}INFO$grey] $reset$msg")
    }

    /**
     * Log a text as warn log
     * @param msg The logging text
     */
    fun warn(msg: String) {
        println("$grey[${getFormattedTime()}]: [${orange}WARNING$grey] $reset$msg")
    }

    /**
     * Log a text as error log
     * @param msg The logging text
     */
    fun error(msg: String) {
        println("$grey[${getFormattedTime()}]: [${red}ERROR$grey] $red$msg")
    }

    /**
     * Log a text as debug log
     * @param msg The logging text
     */
    fun debug(msg: String) {
        if (enableDebug) {
            println("$grey[${getFormattedTime()}]: [${cyan}DEBUG$grey] $reset$msg")
        }
    }

    /**
     * Log a text as art-net log
     * @param msg The logging text
     */
    fun artNet(msg: String) {
        if (enableArtNet) {
            println("$grey[${getFormattedTime()}]: [${purple}ART-NET$grey] $reset$msg")
        }
    }

    /**
     * Log a text as logic log
     * @param msg The logging text
     */
    fun logic(msg: String) {
        if (enableLogic) {
            println("$grey[${getFormattedTime()}]: [${yellow}LOGIC$grey] $reset$msg")
        }
    }

}