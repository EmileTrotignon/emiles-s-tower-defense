package GUI

import java.awt.BorderLayout

import GameLogic.GameLogic
import javax.swing._

class Lobby(name: String) extends JFrame(name)
{
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val start_game_signal: FSignal[GameLogic] = new FSignal[GameLogic]

    val content_pane = new JPanel(new BorderLayout)

    val panel_start_level = new JPanel()
    GameInstance.level_loaders.foreach(
        level_loader =>
        {
            val level = level_loader()
            panel_start_level.add(new FButton(level.name, _ => start_game_signal.emit(new GameLogic(level_loader()))))
        }
    )
    content_pane.add(panel_start_level)
    this.setContentPane(content_pane)

}
