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



  val panel_player_turn = new JPanel()

  val build_tower_button = new JButton("Build tower")
  build_tower_button.setAction(new ActionFromFunction(select_square))
  panel_player_turn.add(build_tower_button)

  def start_player_turn(): Unit =
  {
    contentPane.add(panel_player_turn, BorderLayout.PAGE_END)
    this.setContentPane(contentPane)
  }

  val next_level_button = new JButton("Start next level")
  panel_player_turn.add(next_level_button)
  next_level_button.setAction(new ActionFromFunction(start_next_level))

  def start_next_level(action_event : ActionEvent): Unit =
  {
    contentPane.remove(panel_player_turn)
    this.setContentPane(contentPane)
    if(!(this.game_logic.start_next_level()))
      congratulate()
  }



  val panel_select_square = new JPanel()

  val cancel_select_square_button = new JButton("Cancel")
  cancel_select_square_button.setAction(new ActionFromFunction(cancel_select_square))
  panel_select_square.add(cancel_select_square_button)

  def select_square(action_event : ActionEvent): Unit =
  {
    contentPane.remove(panel_player_turn)

    /* TODO
     ici il faut faire que les carrés soient cliquables et lancent select_tower
     */

    this.setContentPane(contentPane)
  }

  def cancel_select_square(action_event : ActionEvent): Unit =
  {
    contentPane.remove(panel_select_square)
    panel_select_square.removeAll()
    panel_select_tower.add(cancel_select_tower_button)
    contentPane.add(panel_player_turn)
    this.setContentPane(contentPane)
  }



  val panel_select_tower = new JPanel()

  val cancel_select_tower_button = new JButton("Cancel")
  cancel_select_tower_button.setAction(new ActionFromFunction(cancel_select_tower))
  panel_select_tower.add(cancel_select_tower_button)

  def select_tower(action_event : ActionEvent): Unit =
  {

    /* TODO
     ici il faut faire que les carrés cessent d'être cliquables
     */

    Towers.show_available(panel_select_tower)
    this.setContentPane(contentPane)
  }

  def cancel_select_tower(action_event : ActionEvent): Unit =
  {
    panel_select_tower.removeAll()
    panel_select_tower.add(cancel_select_tower_button)
    contentPane.add(panel_select_square)
    this.setContentPane(contentPane)
  }



  def congratulate(): Unit =
  {
    /* TODO
     ici il faut afficher qu'il n'y a plus d'autre niveau
     */
  }




  this.start_player_turn()
}
