package GUI

import javax.swing._
import java.awt.{BorderLayout, Canvas, Graphics}

import GameLogic._

import java.awt.event.ActionEvent


class GameWindow extends JFrame("Emiles's Tower Defense")
{
  val game_logic = new GameLogic(new GameMap(Array(
    Array(true, true, false, true, true),
    Array(true, true, false, true, true),
    Array(true, false, false, true, true),
    Array(true, true, false, true, true),
    Array(true, true, false, true, true),
    Array(true, true, false, true, true)))
    , 10
    , 10
    , List())
  val canvas = new GameBoardCanvas(game_logic)

  import javax.swing.JPanel
  import java.awt.BorderLayout

  val contentPane = new JPanel(new BorderLayout)
  contentPane.add(canvas, BorderLayout.CENTER)
  //contentPane.setBorder(someBorder)

  val panel_player_actions = new PlayerActionsPanel(game_logic)
  val panel_build_towers = new BuildTowersPanel()
  contentPane.add(panel_player_actions, BorderLayout.PAGE_END)
  contentPane.add(panel_build_towers, BorderLayout.EAST)

  def start_player_turn(): Unit =
  {
    this.setContentPane(contentPane)
  }

  def select_tower(action_event : ActionEvent): Unit =
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
