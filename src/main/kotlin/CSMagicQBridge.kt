import java.awt.event.KeyEvent

object CSMagicQBridge {

    var activeLinks: MutableList<Link> = mutableListOf()
    private var activeUniverses: MutableList<Int> = mutableListOf()

    @JvmStatic
    fun main(args: Array<String>) {
        println("Starting...")

        for (link in activeLinks) {
            activeUniverses.add(link.universe)
        }

        ColorSourceListener.startListening(activeUniverses.toIntArray())

        println("Started!")
    }

}