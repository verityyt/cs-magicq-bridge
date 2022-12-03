import utils.Configuration
import utils.Logger

object CSMagicQBridge {

    var activeLinks: MutableList<Link> = mutableListOf()
    private var activeUniverses: MutableList<Int> = mutableListOf()

    @JvmStatic
    fun main(args: Array<String>) {
        Logger.info("Starting system...")

        Configuration.initialize()
        Logger.configure()

        for (link in activeLinks) {
            activeUniverses.add(link.universe)
        }

        Logger.info("Found: ${activeLinks.size} links on ${activeUniverses.size} universes!")

        ColorSourceListener.startListening(activeUniverses.toIntArray())
    }

}