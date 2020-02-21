package GUI

import java.awt.event.ActionEvent

import javax.swing._

class PlayerActionsPanel(val board_logic : GameLogic.GameLogic) extends JPanel
{
    val next_level_button = new JButton()
    this.add(next_level_button)
    next_level_button.setAction(new FAction(start_next_level))
    next_level_button.setText("Start next level")

    def start_next_level(action_event : ActionEvent): Unit =
    {
        board_logic.spawn_monster(GameLogic.Triangle(new GameLogic.Point2DDouble(0.5, 0.01)))
        /*contentPane.remove(panel_player_actions)
        this.setContentPane(contentPane)
        if(!(this.game_logic.start_next_level()))
            congratulate()*/
    }
}
