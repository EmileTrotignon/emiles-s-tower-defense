package GUI

import java.awt.event._

class FTimer(interval: Int, f: ActionEvent => Unit) extends javax.swing.Timer(interval, new FActionListener(f))
{
    def add_action_listener(f: ActionEvent => Unit): Unit =
        addActionListener(new FActionListener(f))
}