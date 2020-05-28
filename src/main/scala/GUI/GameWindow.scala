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
    val panel_tile_actions = new TileActionsPanel(canvas)
    content_pane.add(panel_player_actions, BorderLayout.PAGE_END)
    content_pane.add(panel_tile_actions, BorderLayout.EAST)
    this.setContentPane(content_pane)
    game_logic.level.updated_signal.add_callback(panel_player_actions.info_panel.update_level_labels)
    game_logic.player.updated_signal.add_callback(panel_player_actions.info_panel.update_player_labels)
    game_logic.player.updated_signal.add_callback(panel_tile_actions.update_affordable_towers)
    game_logic.player.updated_signal.emit(game_logic.player)
    game_logic.player.you_lost_signal.add_callback(end_level("You lost"))
    game_logic.you_win_signal.add_callback(end_level("You win !"))
    game_logic.new_wave_signal.add_callback(set_starting_next_wave(false))
    game_logic.last_subwave_but_not_last_wave_signal.add_callback(set_starting_next_wave(true))
    canvas.tile_action_performed_signal.add_callback(_ =>
    {
        panel_tile_actions.cancel_button.setVisible(false)
        panel_tile_actions.enable_every_tile_action()
    })
    
    def set_starting_next_wave(b: Boolean)(u: Unit): Unit = panel_player_actions.next_wave_button.setVisible(b) 
    
    def end_level(message: String)(u: Unit): Unit =
    {
        game_logic.timer.stop()
        JOptionPane.showMessageDialog(this, message)
        this.setVisible(false)
        end_game()
        canvas.timer.stop()
        //System.exit(0)
    }
}