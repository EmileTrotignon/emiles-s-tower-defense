package GUI

import java.awt.event.ActionEvent

import javax.swing._

class PlayerActionsPanel(val game_logic : GameLogic.GameLogic) extends JPanel
{
    val next_wave_button = new JButton()

    val player_info_panel  = new PlayerInfoPanel(game_logic.player)
    this.add(player_info_panel)
    this.add(next_wave_button)
    next_wave_button.setAction(new FAction(start_next_wave))
    next_wave_button.setText("Start next wave")

    def start_next_wave(action_event : ActionEvent): Unit =
    {
        game_logic.start_next_wave()
    }
}
