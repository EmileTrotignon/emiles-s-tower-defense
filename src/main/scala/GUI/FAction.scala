package GUI
import java.awt.event.ActionEvent
import javax.swing._


class FAction(f : ActionEvent => Unit) extends AbstractAction
{
    def actionPerformed(action_event : ActionEvent): Unit =
    {
        f(action_event)
    }
}
