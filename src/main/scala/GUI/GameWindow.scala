package GUI

import GameLogic._
import javax.swing._


class GameWindow(val game_logic: GameLogic, val end_game: Unit => Unit) extends JFrame("Emiles's Tower Defense")
{
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val canvas = new GameBoardCanvas(game_logic)

    import java.awt.BorderLayout

    import javax.swing.JPanel

    val content_pane = new JPanel(new BorderLayout)
    content_pane.add(canvas, BorderLayout.CENTER)
    //contentPane.setBorder(someBorder)

    val panel_player_actions = new PlayerActionsPanel(game_logic)
    val panel_build_towers = new BuildTowersPanel(canvas)
    content_pane.add(panel_player_actions, BorderLayout.PAGE_END)
    content_pane.add(panel_build_towers, BorderLayout.EAST)
    this.setContentPane(content_pane)
    game_logic.player.updated_signal.add_callback(panel_player_actions.player_info_panel.update_labels)
    game_logic.player.updated_signal.add_callback(panel_build_towers.update_affordable_towers)
    game_logic.player.updated_signal.emit(game_logic.player)
    game_logic.player.you_lost_signal.add_callback(end_level("You lost"))
    game_logic.you_win_signal.add_callback(end_level("You win !"))
    canvas.tower_built_signal.add_callback(_ =>
    {
        panel_build_towers.cancel_button.setVisible(false)
        panel_build_towers.enable_every_tower()
    })

    def end_level(message: String)(u: Unit): Unit =
    {
        JOptionPane.showMessageDialog(this, message)
        this.setVisible(false)
        end_game()
        //System.exit(0)
    }

}
