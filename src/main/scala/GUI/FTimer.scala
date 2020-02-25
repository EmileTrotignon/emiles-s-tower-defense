package GUI

import java.awt.event._

class FActionListener(f: ActionEvent => Unit) extends ActionListener
{
    override def actionPerformed(action_event: ActionEvent): Unit =
    {
        f(action_event)
    }
}

class FTimer(interval: Int, f: ActionEvent => Unit) extends javax.swing.Timer(interval, new FActionListener(f))
{
    def add_action_listener(f: ActionEvent => Unit): Unit =
        addActionListener(new FActionListener(f))
}