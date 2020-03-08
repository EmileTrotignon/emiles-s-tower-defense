package GameLogic

case class SizeInfo(map_size: Int2)
{


    def this(map: GameMap) =
    {
        this(map.size)
    }

    def logic_to_square(p: Double2): Int2 =
    {
        Double2.floor(p)
    }


    def square_center(p: Int2): Double2 =
    {
        Double2(p.x.toDouble + 0.5, p.y.toDouble + 0.5)
    }

    def square_corner(p: Int2): Double2 =
    {
        p.toDouble2
    }
}

class SizeInfoPixels(map: GameMap, val graphics_bounds: java.awt.Rectangle) extends SizeInfo(map)
{

    def square_size: Int2 =
    {
        Int2(graphics_bounds.width / map_size.x, graphics_bounds.height / map_size.y)
    }

    def pixels_to_logic(p: Int2): Double2 =
    {
        val pdouble = p.toDouble2
        Double2(pdouble.x * map_size.x / graphics_bounds.width, pdouble.y * map_size.y / graphics_bounds.height)
    }

    def logic_to_pixels(p: Double2): Int2 =
    {
        Double2.round(new Double2(p.x * graphics_bounds.width / map_size.x, p.y * graphics_bounds.height / map_size.y))
    }

    def pixels_to_square(p: Int2): Int2 =
    {
        logic_to_square(pixels_to_logic(p))
    }
}

