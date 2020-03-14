package Graphics

import java.awt.Graphics2D

import GameLogic.{Double2, SizeInfoPixels}

class Drawing(val elements: Array[DrawingElement])
{
    def paint(size_info: SizeInfoPixels, g: Graphics2D, position: Double2, scale: Double): Unit =
    {
        elements.foreach(e => e.paint(size_info, g, position, scale))
    }
}
