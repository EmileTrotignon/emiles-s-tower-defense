package GUI

import java.awt.{Canvas, Color, Graphics}

import javax.swing.JComponent

class GameBoardCanvas(val game_logic: GameLogic.GameLogic) extends JComponent
{
    this.setBounds(0, 0, 800, 800)

    val square_size: (Int, Int) = (this.getHeight / game_logic.board.map.size()._1,
      this.getWidth / game_logic.board.map.size()._2)

    def draw_background(g: Graphics): Unit =
    {
        g.setColor(Color.white)
        g.fillRect(0, 0, this.getHeight, this.getWidth)
        g.setColor(Color.black)

        for
            {i <- 0 until game_logic.board.map.size()._1}
        {
            for
                {j <- 0 until game_logic.board.map.size()._2}
            {
                if (game_logic.board.map.is_buildable(i, j))
                {
                    g.fillRect(i * square_size._1, j * square_size._2,
                        i * (square_size._1 + 1), j * (square_size._2 + 1))
                }
            }
        }
    }

    override def paintComponent(g: Graphics)
    {
        draw_background(g)
    }
}
