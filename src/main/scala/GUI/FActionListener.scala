package GUI

import java.awt.event.{ActionEvent, ActionListener}

class FActionListener(f: ActionEvent => Unit) extends ActionListener
{
    override def actionPerformed(a: ActionEvent): Unit =
    {
        f(a)
    }
}
