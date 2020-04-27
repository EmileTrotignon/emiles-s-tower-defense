package GUI

import GameLogic.PlayerLogic
import javax.swing.{JLabel, JPanel}

class PlayerInfoPanel(player_logic: PlayerLogic)  extends JPanel
{
    val lives_label = new JLabel("Lives : -1")
    val money_label = new JLabel("Money : -1")
    update_labels(player_logic)

    this.add(lives_label)
    this.add(money_label)

    def update_labels(player_logic : PlayerLogic): Unit =
    {
        lives_label.setText(s"Lives : ${player_logic.lives.toString}")
        money_label.setText(s"Money : ${player_logic.money.toString}")
    }
}
