class Link(
    var universe: Int,
    private var channel: Int,
    var hotkey: Hotkey,
    private var exactValue: Int? = null,
    private var rangeStart: Int? = null,
    private var rangeEnd: Int? = null
) {

    var active: Boolean = false

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