package GUI

import java.awt._
import java.awt.event.MouseEvent

import GameLogic.Towers.Tower
import GameLogic.{Double2, Int2, SizeInfo, SizeInfoPixels}
import javax.swing._
import javax.swing.event.MouseInputAdapter


class GameBoardCanvas(val game_logic: GameLogic.GameLogic) extends JComponent
{

    this.setPreferredSize(new Dimension(1000, 1000))
    this.setMinimumSize(new Dimension(200, 200))
    this.setMaximumSize(new Dimension(1080, 1080))

    def get_square_size(): (Int, Int) = (this.getHeight / game_logic.board.map.size.x,
      this.getWidth / game_logic.board.map.size.y)

    this.addMouseListener(GameBoardCanvasMouseListener)
    val timer: Timer = new FTimer(1000 / 60, _ =>
    {
        this.repaint()
    })
    timer.start()

    val tower_built_signal: FSignal[Unit] = new FSignal[Unit]()

    def paint_hovered_square(size_info: SizeInfoPixels, square: Int2, g: Graphics2D): Unit =
    {
        game_logic.board.tower_at_square(square) match
        {
            case None => ()
            case Some(tower) =>
                val center = size_info.logic_to_pixels(tower._position)
                val size = size_info.logic_to_pixels(Double2(tower.reach, tower.reach) * 2)
                val corner = center - (size / 2)
                g.setColor(Color.green)
                g.drawOval(corner.x, corner.y, size.x, size.y)
        }
    }

    override def paintComponent(g: Graphics)
    {
        val size_info = new SizeInfoPixels(game_logic.board.map.size, g.getClipBounds())

        val resolution = Int2(g.getClipBounds.width, g.getClipBounds.height)

        val ratio_width = resolution.x.toDouble / size_info.map_size.x

        val ratio_heigth = resolution.y.toDouble / size_info.map_size.y

        val ratio = Math.min(ratio_heigth, ratio_width)

        val bounds = Double2.floor(size_info.map_size.toDouble2 * ratio)

        setBounds(0, 0, bounds.x, bounds.y)
        val g2 = g.asInstanceOf[Graphics2D]

        for (layer <- GameLogic.Layers.min_layer to GameLogic.Layers.max_layer)
            game_logic.board.paint_board(size_info, layer, g2)


        val location = new Int2(MouseInfo.getPointerInfo.getLocation) - new Int2(this.getLocation())

        if (location.is_in_bounds(getBounds()))
        {
            val square = size_info.pixels_to_square(location)
            paint_hovered_square(size_info, square, g2)
        }
        Toolkit.getDefaultToolkit.sync() // Very important for speed
    }

    object GameBoardCanvasMouseListener extends MouseInputAdapter
    {

        var status: GameBoardCanvas.GameBoardCanvasStatus = GameBoardCanvas.Idle()

        override def mouseClicked(e: MouseEvent): Unit =
        {
            val size_info = new SizeInfoPixels(game_logic.board.map.size, GameBoardCanvas.this.getBounds())
            status match
            {
                case GameBoardCanvas.Idle() => ()
                case GameBoardCanvas.BuildingTower(cost, constructor) =>
                    val pos = new Int2(e.getX, e.getY)
                    val pos_square = size_info.pixels_to_square(pos)
                    if (game_logic.board.map.is_buildable(pos_square) && game_logic.board.tower_at_square(pos_square).isEmpty)
                    {
                        game_logic.build_tower(pos_square, cost, constructor)
                        status = GameBoardCanvas.Idle()
                        tower_built_signal.emit()
                    }

            }
        }

        override def mouseMoved(mouseEvent: MouseEvent): Unit =
        {

        }
    }

}

object GameBoardCanvas
{

    class GameBoardCanvasStatus
    {

    }

    case class BuildingTower(cost: Double, constructor: (Int2, SizeInfo) => Tower) extends GameBoardCanvasStatus
    {

    }

    case class Idle() extends GameBoardCanvasStatus
    {

    }

}

