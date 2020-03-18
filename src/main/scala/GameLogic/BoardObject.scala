package GameLogic

import java.awt.Graphics2D

abstract class BoardObject
{
    protected var _position: Double2 //position sur l'image (pas sur la grille)

    protected val drawing: Graphics.Drawing

    protected val _size: Double

    protected var _direction: Double2 = Double2(1, 0)

    def direction: Double2 = _direction

    def size: Double = _size

    def position: Double2 = _position

    def paint(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        if (direction == Double2(1, 0))
            drawing.paint(size_info, g, _position, size)
        else
        {
            drawing.paint(size_info, g, _position, size, direction.angle)
        }
    }

    def tick(b: BoardLogic): Unit

}

