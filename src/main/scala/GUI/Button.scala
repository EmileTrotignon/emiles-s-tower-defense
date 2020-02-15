package GUI

import java.awt.event.ActionEvent

import javax.swing.JButton

class Button(text : String, action : ActionEvent => Unit) extends JButton
{
    this.setAction(new ActionFromFunction(action))
    this.setText(text)
}
