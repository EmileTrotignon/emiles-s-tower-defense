package GUI

import java.awt.event.ActionEvent

import GameLogic._
import javax.swing._


class GameWindow extends JFrame("Emiles's Tower Defense")
{
    val game_logic = new GameLogic(new GameMap(Array(
        Array(true, true, true, true, false, true, true, true, true),
        Array(true, true, true, true, false, true, true, true, true),
        Array(true, true, true, false, false, true, true, true, true),
        Array(true, true, true, true, false, true, true, true, true),
        Array(true, true, true, true, false, true, true, true, true),
        Array(true, true, true, true, false, true, true, true, true),
        Array(true, true, true, true, false, true, true, true, true),
        Array(true, true, true, true, false, true, true, true, true),
        Array(true, true, true, true, false, true, true, true, true)))
        , 10
        , 10
        , List())
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

    def start_player_turn(): Unit =
    {
        this.setContentPane(contentPane)
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


    this.start_player_turn()
}
