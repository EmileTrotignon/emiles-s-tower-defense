package GameLogic

import scala.io.{BufferedSource, Source}
import java.awt.{Color, Graphics2D, Rectangle}

import scala.collection.mutable.ArrayBuffer

class GameMap(private val map: Array[Array[MapTile]])
{

    def size: Int2 =
    {
        Int2(map(0).length, map.length)
    }

    def width(): Int =
    {
        map.length
    }


    def height(): Int =
    {
        map(0).length
    }

    def is_buildable(s: Int2): Boolean =
    {
        get_tile(s) match
        {
            case TowerTile() => true
            case _ => false
        }
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

        for
            {i <- 0 until this.size.x
             j <- 0 until this.size.y
             }
        {
            val sq = Int2(i, j)
            val pos_pixels = size_info.logic_to_pixels(size_info.square_corner(sq))
            get_tile(sq) match
            {
                case TowerTile() =>
                    g.setColor(Color.black)
                    g.fillRect(pos_pixels.x, pos_pixels.y, size_info.square_size.x, size_info.square_size.y)
                    g.setColor(Color.darkGray)
                    g.drawRect(pos_pixels.x, pos_pixels.y, size_info.square_size.x, size_info.square_size.y)
                case BaseTile() =>
                    g.setColor(Color.red)
                    g.fillRect(pos_pixels.x, pos_pixels.y, size_info.square_size.x, size_info.square_size.y)
                case _ => ()
            }
        }
    }

}

object GameMap
{
    def from_file(source: BufferedSource): GameMap =
    {
        def string_to_row(s: String): Array[MapTile] =
        {
            s.toCharArray.map
            {
                case 'T' => TowerTile()
                case 'B' => BaseTile()
                case 'M' => MonsterTile()
            }
        }

        val a: Array[Array[MapTile]] = source.getLines().map(string_to_row).toArray
        new GameMap(a)
    }
}
