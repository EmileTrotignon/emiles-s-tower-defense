package GUI

import java.awt.event.ActionEvent

import GameLogic._
import javax.swing._


class GameWindow extends JFrame("Emiles's Tower Defense")
{
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    val game_logic = new GameLogic(/*new GameMap(Array(
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), MonsterTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), MonsterTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), BaseTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile())))*/ GameInstance.map
        , 10
        , 10
        , GameLogic.Levels.levels)
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
    game_logic.player.you_lost_signal.add_callback(you_lost)
    canvas.tower_built_signal.add_callback(_ =>
    {
        panel_build_towers.cancel_button.setVisible(false)
        panel_build_towers.enable_every_tower()
    })

    def you_lost(u: Unit): Unit =
    {
        JOptionPane.showMessageDialog(this, "You lost")
        System.exit(0)
    }

}
