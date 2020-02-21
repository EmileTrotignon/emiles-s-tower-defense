package GUI

import java.awt.event.ActionEvent

import javax.swing.JButton

class FButton(text : String, action : ActionEvent => Unit) extends JButton
{
    this.setAction(new FAction(action))
    this.setText(text)
}
