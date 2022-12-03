import ch.bildspur.artnet.ArtNetBuffer
import ch.bildspur.artnet.ArtNetClient
import ch.bildspur.artnet.events.ArtNetServerEventAdapter
import ch.bildspur.artnet.packets.ArtNetPacket

object ColorSourceListener {

    private val artNetBuffer = ArtNetBuffer()
    private val artNetClient = ArtNetClient(artNetBuffer)

    fun startListening(universes: IntArray) {
        artNetClient.artNetServer.addListener(object : ArtNetServerEventAdapter() {
            override fun artNetPacketReceived(packet: ArtNetPacket?) {
                if (packet !== null) {
                    for (universe in universes) {
                        handleIncomingPacketsOfUniverse(packet, universe)
                    }
                } else {
                    println("[WARNING]: Received packet is null")
                }
            }
        })

        artNetClient.start()
    }

    /**
     * Method to handle incoming packets for specified universe
     * @param packet The incoming [ArtNetPacket]
     * @param universe The universe to handle
     */
    private fun handleIncomingPacketsOfUniverse(packet: ArtNetPacket, universe: Int) {
        println("[UNIVERSE $universe]: New packet received - ${packet.data}")

        val encodedData: ByteArray = artNetClient.readDmxData(0, universe)
        val decodedData: MutableList<Int> = mutableListOf()

        for (address in 0..511) {
            decodedData.add(encodedData[address].toInt() and 0xFF)
        }

        handleEncodedData(decodedData.toIntArray(), universe)
    }

    /**
     * Handles encoded data
     * @param data Encoded data as [IntArray]
     */
    private fun handleEncodedData(data: IntArray, universe: Int) {
        println("[UNIVERSE $universe]: Decoded - ${data.joinToString()}")
        Logic.handleCSInput(DataPackage(data, universe))
    }

}