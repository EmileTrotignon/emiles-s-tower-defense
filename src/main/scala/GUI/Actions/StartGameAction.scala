package GUI.Actions

import java.awt.event.ActionEvent

import javax.swing._

object StartGameAction extends AbstractAction
{
    def actionPerformed(action_event: ActionEvent): Unit =
    {
          GUI.Main.start_game()
    }

}
