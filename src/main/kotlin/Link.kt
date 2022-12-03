class Link(
    var universe: Int,
    private var channel: Int,
    var hotkey: Hotkey,
    private var exactValue: Int? = null,
    private var rangeStart: Int? = null,
    private var rangeEnd: Int? = null
) {

    /**
     * Whether the link is currently active or not
     */
    var active: Boolean = false

    /**
     * @param dataPackage [DataPackage] which contains the newest art-net data
     * @return true If link requirements are fulfilled
     */
    fun checkLink(dataPackage: DataPackage): Boolean {
        if (universe == dataPackage.universe) {
            val data = dataPackage.data[channel - 1]

            if (exactValue !== null) {
                if (data == exactValue) {
                    return true
                }
            } else if (rangeStart !== null && rangeEnd !== null) {
                if (data >= rangeStart!! && data <= rangeEnd!!) {
                    return true
                }
            }
        }

        return false
    }

}