import ch.bildspur.artnet.ArtNetBuffer
import ch.bildspur.artnet.ArtNetClient
import ch.bildspur.artnet.events.ArtNetServerEventAdapter
import ch.bildspur.artnet.packets.ArtNetPacket
import utils.Logger

object ColorSourceListener {

    private val artNetBuffer = ArtNetBuffer()
    private val artNetClient = ArtNetClient(artNetBuffer)

    fun startListening(universes: IntArray) {
        Logger.debug("Starting art-net client and adding listener...")
        Logger.debug("Listening on following universe(s): ${universes.joinToString()}")

        artNetClient.artNetServer.addListener(object : ArtNetServerEventAdapter() {
            override fun artNetPacketReceived(packet: ArtNetPacket?) {
                if (packet !== null) {
                    for (universe in universes) {
                        handleIncomingPacketsOfUniverse(packet, universe)
                    }
                } else {
                    Logger.warn("Received packet is null")
                }
            }
        })

        try {
            artNetClient.start()
            Logger.info("Art-Net client successfully started!")
        }catch (e: Exception) {
            Logger.error("Error while starting art-net client (${e.message})")
        }
    }

    /**
     * Method to handle incoming packets for specified universe
     * @param packet The incoming [ArtNetPacket]
     * @param universe The universe to handle
     */
    private fun handleIncomingPacketsOfUniverse(packet: ArtNetPacket, universe: Int) {
        Logger.artNet("Received new ${packet.type} packet on universe $universe")
        Logger.artNet("[DEBUG]: Decoding new packet...")

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
        Logger.artNet("[DEBUG]: Sending data package to logic...")
        Logic.handleCSInput(DataPackage(data, universe))
    }

}