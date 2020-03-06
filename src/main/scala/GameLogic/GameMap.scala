package GameLogic

import java.awt.{Color, Graphics2D, Rectangle}

class GameMap(val map: Array[Array[MapTile]])
{
    def size(): Int2 =
    {
        Int2(map.length, map(0).length)
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
        map(j)(i) match
        {
            case TowerTile() => true
            case _ => false
        }
    }

    def is_buildable(s: Int2): Boolean =
    {
        is_buildable(s.x, s.y)
    }

    def get_tile(i: Int, j: Int): MapTile =
    {
        map(j)(i)
    }

    def get_tile(s: Int2): MapTile =
    {
        get_tile(s.x, s.y)
    }

    def paint_map(size_info: SizeInfoPixels, g: Graphics2D): Unit =
    {
        g.setColor(Color.gray)
        g.fillRect(0, 0, size_info.graphics_bounds.width, size_info.graphics_bounds.height)
        val square_size = size_info.square_size
        for
            {i <- 0 until this.size().x
             j <- 0 until this.size().y
             }
        {
            val x = j * square_size.x
            val y = i * square_size.y
            get_tile(j, i) match
            {
                case TowerTile() =>
                    g.setColor(Color.black)
                    g.fillRect(x, y, square_size.x, square_size.y)
                    g.setColor(Color.darkGray)
                    g.drawRect(x, y, square_size.x, square_size.y)
                case BaseTile() =>
                    g.setColor(Color.red)
                    g.fillRect(x, y, square_size.x, square_size.y)
                case _ => ()
            }
            if (is_buildable(j, i))
            {

            }
        }
    }

}
