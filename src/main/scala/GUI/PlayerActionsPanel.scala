package GUI

import java.awt.Component
import java.awt.event.ActionEvent

import javax.swing._

class PlayerActionsPanel(val game_logic : GameLogic.GameLogic) extends JPanel
{
    val next_level_button = new JButton()
    this.add(next_level_button)
    next_level_button.setAction(new FAction(start_next_level))
    next_level_button.setText("Start next level")

    def start_next_level(action_event : ActionEvent): Unit =
    {
        game_logic.start_next_level()
        if (game_logic.is_finished)
            congratulate()
    }


    //les lignes suivantes sont a revoir. J'ai essaye tres maladroitemment d'afficher un texte a l'ecran pour terminer le jeu.
    val congratulation_button = new JButton()
    congratulation_button.setAction(new FAction((action_event: ActionEvent) => ()))
    congratulation_button.setText("Congatulation, you have killed all the monsters !")

    def congratulate(): Component =
    {
        this.add(congratulation_button)
    }
}
