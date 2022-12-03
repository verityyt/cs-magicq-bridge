object Logic {

    /**
     * Method to perform links based on the given [dataPackage]
     * @param dataPackage [DataPackage] which contains the current art-net data
     */
    fun handleCSInput(dataPackage: DataPackage) {
        for (link in CSMagicQBridge.activeLinks) {
            if (link.checkLink(dataPackage)) {
                if (!link.active) {
                    link.hotkey.perform()
                }

                link.active = true
            } else {
                link.active = false
            }
        }
    }

}