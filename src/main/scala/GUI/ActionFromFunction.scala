package GUI
import java.awt.event.ActionEvent
import javax.swing._


class ActionFromFunction(f : ActionEvent => Unit) extends AbstractAction
{
    def actionPerformed(action_event : ActionEvent): Unit =
    {
        f(action_event)
    }
}
