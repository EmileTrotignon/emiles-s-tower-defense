package GameLogic.Towers

import java.awt.Graphics2D

import GameLogic._
import Graphics.Drawing

abstract class Tower(val square: Int2 = null, size_info: SizeInfo) extends BoardObject
{
    override var _position: Double2 = size_info.square_center(square)
    override val _size: Double = 1
    protected val damage: Double
    protected val period: Int
    protected val _reach: Double
    val drawing: Drawing

    override def paint(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        layer match
        {
            case Layers.towers =>
                super.paint(size_info, layer, g)
            case _ => ()
        }
    }

    def reach: Double = _reach

}
