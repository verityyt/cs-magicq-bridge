object Logic {

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