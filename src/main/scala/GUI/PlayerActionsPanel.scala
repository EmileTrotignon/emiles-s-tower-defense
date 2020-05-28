package GUI

import java.awt.event.ActionEvent

import javax.swing._

class PlayerActionsPanel(val game_logic : GameLogic.GameLogic) extends JPanel
{
    val next_wave_button = new JButton()
    val abandon_button = new JButton()

    val info_panel  = new InfoPanel(game_logic)
    this.add(info_panel)
    this.add(next_wave_button)
    this.add(abandon_button)
    next_wave_button.setAction(new FAction(start_next_wave))
    next_wave_button.setText("Start next wave")
    abandon_button.setAction(new FAction(abandon))
    abandon_button.setText("Abandon")

    def start_next_wave(action_event : ActionEvent): Unit =
    {
        game_logic.start_next_wave()
    }
    
    def abandon(action_event : ActionEvent): Unit =
    {
        game_logic.abandon()
    }
}
