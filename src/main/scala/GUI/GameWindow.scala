package GUI

import java.awt.event.ActionEvent

import GameLogic._
import javax.swing._


class GameWindow extends JFrame("Emiles's Tower Defense")
{
    val game_logic = new GameLogic(new GameMap(Array(
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), MonsterTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), MonsterTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile()),
        Array(TowerTile(), TowerTile(), TowerTile(), TowerTile(), BaseTile(), TowerTile(), TowerTile(), TowerTile(), TowerTile())))
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

    game_logic.player.updated_signal.add_callback(panel_player_actions.player_info_panel.update_labels)

    def start_player_turn(): Unit =
    {
        this.setContentPane(content_pane)
    }

    def select_tower(action_event: ActionEvent): Unit =
    {

        /* TODO
         ici il faut faire que les carrés cessent d'être cliquables
         */

    }


    def congratulate(): Unit =
    {
        /* TODO
         ici il faut afficher qu'il n'y a plus d'autre niveau
         */
    }
}
