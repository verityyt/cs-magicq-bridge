import utils.Configuration
import utils.Logger

object CSMagicQBridge {

    /**
     * List of all links (imported via [Configuration])
     */
    var activeLinks: MutableList<Link> = mutableListOf()

    /**
     * List of all universes used in [activeLinks]
     */
    private var activeUniverses: MutableList<Int> = mutableListOf()

    @JvmStatic
    fun main(args: Array<String>) {
        Logger.info("Starting system...")

        Configuration.initialize()
        Logger.configure()

        Logger.debug("Importing links from configuration...")
        for (link in Configuration.config.links) {
            activeLinks.add(link)

            if (!activeUniverses.contains(link.universe)) {
                activeUniverses.add(link.universe)
            }
        }

        Logger.info("Imported ${activeLinks.size} link(s) on ${activeUniverses.size} universe(s)!")

        ColorSourceListener.startListening(activeUniverses.toIntArray())
    }

}