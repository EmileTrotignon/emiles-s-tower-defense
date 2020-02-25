package GUI

import java.awt.event.{MouseEvent, MouseListener}
import java.awt.{Dimension, Graphics, Graphics2D, Toolkit}

import GameLogic.{GameMap, Int2, SizeInfo, Tower}
import javax.swing._


class GameBoardCanvasStatus
{

}

case class BuildingTower(constructor: (Int2, GameMap) => Tower) extends GameBoardCanvasStatus
{

}

case class Idle() extends GameBoardCanvasStatus
{

}


class GameBoardCanvas(val game_logic: GameLogic.GameLogic) extends JComponent
{

    this.setPreferredSize(new Dimension(1000, 1000))

    def get_square_size(): (Int, Int) = (this.getHeight / game_logic.board.map.size()._1,
      this.getWidth / game_logic.board.map.size()._2)

    this.addMouseListener(GameBoardCanvasMouseListener)
    val timer: Timer = new FTimer(1000 / 60, a =>
    {
        this.repaint()
    })
    timer.start()

    override def paintComponent(g: Graphics)
    {
        val minsize = math.min(g.getClipBounds.width, g.getClipBounds.height)
        setBounds(0, 0, minsize, minsize)
        val g2 = g.asInstanceOf[Graphics2D]
        val size_info = new SizeInfo(game_logic.board.map, g.getClipBounds())
        game_logic.board.paint_board(size_info, g2)
        Toolkit.getDefaultToolkit.sync() // Very important for speed
    }

    object GameBoardCanvasMouseListener extends MouseListener
    {

        var status: GameBoardCanvasStatus = Idle()

        def mouseEntered(e: MouseEvent): Unit =
        {

        }

        def mouseExited(e: MouseEvent): Unit =
        {

        }

        def mouseReleased(e: MouseEvent): Unit =
        {

        }

        def mouseClicked(e: MouseEvent): Unit =
        {
            status match
            {
                case Idle() => ()
                case BuildingTower(constructor) =>
                    val pos = new Int2(e.getX, e.getY)
                    val pos_square = Int2.pixels_to_squares(pos, getWidth, getHeight, game_logic.board.map.width(), game_logic.board.map.height())
                    if (game_logic.board.map.is_buildable(pos_square.x, pos_square.y))
                    {
                        val tower = constructor(pos_square, game_logic.board.map)
                        game_logic.spawn_tower(tower)
                        status = Idle()
                    }
            }
        }

        def mousePressed(e: MouseEvent): Unit =
        {

        }

    }

}
