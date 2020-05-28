package GUI

import GameLogic.{GameLogic, PlayerLogic, Level}
import javax.swing.{JLabel, JPanel}

class InfoPanel(game_logic: GameLogic)  extends JPanel
{
    val lives_label = new JLabel("Lives : -1")
    val money_label = new JLabel("Money : -1")
    val wave_label = new JLabel("Wave : -1")
    update_player_labels(game_logic.player)
    update_level_labels(game_logic.level)

    this.add(lives_label)
    this.add(money_label)
    this.add(wave_label)

    def update_player_labels(player_logic : PlayerLogic): Unit =
    {
        lives_label.setText(s"Lives : ${player_logic.lives.toString}")
        money_label.setText(s"Money : ${player_logic.money.toString}")
    }
    
    def update_level_labels(level: Level): Unit =
    {
        wave_label.setText(s"Wave : ${level.wave_number.toString}")
    }
}
