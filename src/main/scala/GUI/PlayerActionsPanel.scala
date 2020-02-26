package GUI

import java.awt.event.ActionEvent

import javax.swing._

class PlayerActionsPanel(val game_logic : GameLogic.GameLogic) extends JPanel
{
    val next_level_button = new JButton()

    val player_info_panel  = new PlayerInfoPanel(game_logic.player)
    this.add(player_info_panel)
    this.add(next_level_button)
    next_level_button.setAction(new FAction(start_next_level))
    next_level_button.setText("Start next level")

    def start_next_level(action_event : ActionEvent): Unit =
    {
        game_logic.start_next_level()
    }
}
