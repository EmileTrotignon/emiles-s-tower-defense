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

    val contentPane = new JPanel(new BorderLayout)
    contentPane.add(canvas, BorderLayout.CENTER)
    //contentPane.setBorder(someBorder)

    val panel_player_actions = new PlayerActionsPanel(game_logic)
    val panel_build_towers = new BuildTowersPanel(canvas)
    contentPane.add(panel_player_actions, BorderLayout.PAGE_END)
    contentPane.add(panel_build_towers, BorderLayout.EAST)
    game_logic.player_logic.updated_signal.add_callback(panel_build_towers.update_afordable_towers)
    
    this.setContentPane(contentPane)

    def congratulate(): Unit =
    {
        /* TODO
         ici il faut afficher qu'il n'y a plus d'autre niveau
         */
    }
}
