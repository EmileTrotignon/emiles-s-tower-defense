package GameLogic

import scala.io.{BufferedSource, Source}
import java.awt.{Color, Graphics2D, Rectangle}

import scala.collection.mutable.ArrayBuffer

class GameMap(val map: Array[Array[MapTile]])
{
    def size: Int2 =
    {
        Int2(map(0).length, map.length)
    }

    def width: Int =
    {
        size.x
    }

    def height: Int =
    {
        size.y
    }

    def is_buildable(square: Int2): Boolean =
    {
        get_tile(square) match
        {
            case TowerTile() => true
            case _ => false
        }
    }

    def get_tile(i: Int, j: Int): MapTile =
    {
        map(j)(i)
    }

    def get_tile(square: Int2): MapTile =
    {
        get_tile(square.x, square.y)
    }

    def set_tile(square: Int2, tile: MapTile): Boolean =
    {
        map(square.y)(square.x) = tile
        routes.update_routes()
    }
    
    def is_convertible(square: Int2, player_side: Boolean): Boolean =
    {
        if (player_side)
        {
            get_tile(square) match
            {
                case MonsterTile() =>
                    val test_map = new GameMap(map.map(row => row.map(tile => tile)))
                    test_map.set_tile(square, TowerTile())
                case _ => false
            }
        }
        else
        {
            get_tile(square) match
            {
                case TowerTile() => true
                case _ => false
            }
        }
    }

    def paint_map(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        layer match
        {
            case Layers.map =>

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
                        case SpecialMonsterTile() =>
                            g.setColor(Color.white)
                            g.fillRect(pos_pixels.x, pos_pixels.y, size_info.square_size.x, size_info.square_size.y)
                        case _  => ()
                    }
                }
            case _ => ()
        }
    }

    def iterator: Seq[Int2] =
    {
        for
            {i <- 0 until size.x
             j <- 0 until size.y
             } yield Int2(i, j)
    }

    val routes = new Routes(this)
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
                case 'S' => SpecialMonsterTile()
                case _ => SpecialMonsterTile() //c'est important de mettre ce cas pour ne pas avoir une erreur incomprehensible a l'execution quand on s'est trompe dans l'ecriture du fichier mapN
            }
        }

        val a: Array[Array[MapTile]] = source.getLines().map(string_to_row).toArray
        new GameMap(a)
    }
}
