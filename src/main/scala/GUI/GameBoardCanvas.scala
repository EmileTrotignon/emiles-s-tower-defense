package GUI

import java.awt.event.{MouseEvent, MouseListener}
import java.awt.{Graphics, Graphics2D, Toolkit}

import GameLogic.{GameMap, Point2DInt, Tower}
import javax.swing._


class GameBoardCanvasStatus
{

}

case class BuildingTower(constructor: (Point2DInt, GameMap) => Tower) extends GameBoardCanvasStatus
{

}

case class Idle() extends GameBoardCanvasStatus
{

}


class GameBoardCanvas(val game_logic: GameLogic.GameLogic) extends JComponent
{
    def obtain_square_size() = (this.getHeight / game_logic.board.map.size()._1,
      this.getWidth / game_logic.board.map.size()._2)
      
    var square_size: (Int, Int) = obtain_square_size()

    this.addMouseListener(GameBoardCanvasMouseListener)
    val alarm: Timer = new FTimer(1000/60, a =>
    {
        repaint()
    })
    alarm.start()

    def update_square_size(): Unit =
    {
        square_size = obtain_square_size()
    }

    override def paintComponent(g: Graphics)
    {
        setBounds(0, 0, 1000, 1000)
        update_square_size()
        val g2 = g.asInstanceOf[Graphics2D]
        game_logic.board.paint_board(g2, square_size, this.getBounds(null))
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
                    val pos = new Point2DInt(e.getX, e.getY)
                    val pos_square = Point2DInt.pixels_to_squares(pos, getWidth, getHeight, game_logic.board.map.width(), game_logic.board.map.height())
                    val tower = constructor(pos_square, game_logic.board.map)
                    game_logic.spawn_tower(tower)
                    status = Idle()
            }
        }

        def mousePressed(e: MouseEvent): Unit =
        {

        }

    }

}
