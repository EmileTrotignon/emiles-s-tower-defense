package GameLogic

import java.awt.Graphics2D

abstract class BoardObject
{
    var position: Double2 //position sur l'image (pas sur la grille)

    protected val drawing: Graphics.Drawing

    protected val size: Double

    def paint(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        drawing.paint(size_info, g, position, size)
    }

    def tick(b: BoardLogic): Unit

}

