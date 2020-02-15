package GUI

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{Canvas, Color, Graphics, Graphics2D, Rectangle}

import javax.swing._

class GameBoardCanvas(val game_logic: GameLogic.GameLogic) extends JComponent
{
    var square_size: (Int, Int) = (this.getHeight / game_logic.board.map.size()._1,
      this.getWidth / game_logic.board.map.size()._2)


    val alarm : Timer = new FTimer(100, a => {repaint()})
    alarm.start()
    def update_square_size(): Unit =
    {
        square_size = (this.getHeight / game_logic.board.map.size()._1,
          this.getWidth / game_logic.board.map.size()._2)
    }

    override def paintComponent(g: Graphics)
    {
        update_square_size()
        val g2 = g.asInstanceOf[Graphics2D]
        game_logic.board.paint_board(g2, square_size, this.getBounds(null))
    }

    def select_square(action_event: ActionEvent): Unit =
    {
        //contentPane.remove(panel_player_turn)

        /* TODO
         ici il faut faire que les carrés soient cliquables et lancent select_tower
         */
    }

}
