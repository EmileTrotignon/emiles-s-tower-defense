package GameLogic

import java.awt.{Color, Graphics2D, Rectangle}

class GameMap(val map: Array[Array[Boolean]])
{
    def size(): (Int, Int) =
    {
        (map.length, map(0).length)
    }

    def width(): Int =
    {
        map.length
    }


    def height(): Int =
    {
        map(0).length
    }

    def is_buildable(i: Int, j: Int): Boolean =
    {
        map(i)(j)
    }

    def paint_map(g: Graphics2D, square_size: (Int, Int), bounds: Rectangle): Unit =
    {
        g.setColor(Color.red)
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
        g.setColor(Color.black)

        for
            {i <- 0 until this.size()._1
             j <- 0 until this.size()._2
             if is_buildable(i, j)
             }
        {
            val x = j * square_size._2
            val y = i * square_size._1
            g.fillRect(x, y, square_size._2, square_size._1)
        }
    }

}
