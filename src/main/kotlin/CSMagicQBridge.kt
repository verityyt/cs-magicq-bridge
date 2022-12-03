import utils.Logger
import java.awt.event.KeyEvent

object CSMagicQBridge {

    var activeLinks: MutableList<Link> = mutableListOf()
    private var activeUniverses: MutableList<Int> = mutableListOf()

    @JvmStatic
    fun main(args: Array<String>) {
        Logger.info("Starting system...")
        Logger.debug("Initializing links...")

        activeLinks.add(Link(0, 1, Hotkey(KeyEvent.VK_H, true, true, true), exactValue = 255))
        activeLinks.add(Link(1, 1, Hotkey(KeyEvent.VK_H, true, true, true), exactValue = 255))

        for (link in activeLinks) {
            activeUniverses.add(link.universe)
        }

        Logger.info("Found: ${activeLinks.size} links on ${activeUniverses.size} universes!")

        ColorSourceListener.startListening(activeUniverses.toIntArray())
    }

}