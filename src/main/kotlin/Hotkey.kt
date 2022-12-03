import java.awt.Robot
import java.awt.event.KeyEvent

class Hotkey(var keyCode: Int, var shift: Boolean, var ctrl: Boolean, var alt: Boolean) {

    /**
     * Perform / Press hotkey
     */
    fun perform() {
        Thread {
            val robot = Robot()

            if (shift) {
                robot.keyPress(KeyEvent.VK_SHIFT)
            }

            if (ctrl) {
                robot.keyPress(KeyEvent.VK_CONTROL)
            }

            if (alt) {
                robot.keyPress(KeyEvent.VK_ALT)
            }

            robot.keyPress(keyCode)

            Thread.sleep(50)

            robot.keyRelease(KeyEvent.VK_SHIFT)
            robot.keyRelease(KeyEvent.VK_CONTROL)
            robot.keyRelease(KeyEvent.VK_ALT)
            robot.keyRelease(keyCode)
        }.start()
    }

}