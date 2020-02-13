package GUI

import java.awt.{Canvas, Color, Graphics, Rectangle}

import javax.swing.JComponent

class GameBoardCanvas(val game_logic: GameLogic.GameLogic) extends JComponent
{
  var square_size: (Int, Int) = (this.getHeight / game_logic.board.map.size()._1,
    this.getWidth / game_logic.board.map.size()._2)


  def update_square_size(): Unit =
  {
    square_size = (this.getHeight / game_logic.board.map.size()._1,
      this.getWidth / game_logic.board.map.size()._2)
  }


  def draw_background(g: Graphics): Unit =
  {
    update_square_size()
    var bounds: Rectangle = this.getBounds(null)

    g.setColor(Color.red)
    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
    g.setColor(Color.black)

    for
    { i <- 0 until game_logic.board.map.size()._1
      j <- 0 until game_logic.board.map.size()._2
      if (game_logic.board.map.is_buildable(i, j))
        }
    {
      val x = j * square_size._2
      val y = i * square_size._1
      g.fillRect(x, y, square_size._2, square_size._1)
    }
  }

  override def paintComponent(g: Graphics)
  {
    draw_background(g)
  }
}
