package GUI

import java.awt.event.{ActionEvent, ActionListener, MouseEvent, MouseListener}
import java.awt.{Canvas, Color, Graphics, Graphics2D, Rectangle, Toolkit}

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
    var square_size: (Int, Int) = (this.getHeight / game_logic.board.map.size()._1,
      this.getWidth / game_logic.board.map.size()._2)

    this.addMouseListener(GameBoardCanvasMouseListener)
    val alarm: Timer = new FTimer(1000/60, a =>
    {
        repaint()
    })
    alarm.start()

    def update_square_size(): Unit =
    {
        square_size = (this.getHeight / game_logic.board.map.size()._1,
          this.getWidth / game_logic.board.map.size()._2)
    }

    override def paintComponent(g: Graphics)
    {
        setBounds(0, 0, 1000, 1000)
        update_square_size()
        val g2 = g.asInstanceOf[Graphics2D]
        game_logic.board.paint_board(g2, square_size, this.getBounds(null))
        Toolkit.getDefaultToolkit.sync()
    }

    def select_square(action_event: ActionEvent): Unit =
    {
        //contentPane.remove(panel_player_turn)

        /* TODO
         ici il faut faire que les carrés soient cliquables et lancent select_tower
         */
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
