package GameLogic

class SizeInfo(map: GameMap, val graphics_bounds: java.awt.Rectangle)
{
    val map_size: (Int, Int) = map.size()

    def square_size: (Int, Int) =
    {
        (graphics_bounds.width / map_size._1, graphics_bounds.height / map_size._2)
    }

    def pixels_to_logic(p: Int2): Double2 =
    {
        assert(false)
        new Double2(0, 0)
    }

    def logic_to_pixels(p: Double2): Int2 =
    {
        p.to_pixels(graphics_bounds.width, graphics_bounds.height)
        Double2.round(new Double2(p.x * graphics_bounds.width, p.y * graphics_bounds.height))
    }

    def logic_to_square(p: Double2): Int2 =
    {
        p.to_square(map_size._1, map_size._2)
    }

    def square_center(p: Int2): Double2 =
    {
        Int2.square_center(p, map_size._1, map_size._2)
    }

    def square_corner(p: Int2): Double2 =
    {
        Int2.square_corner(p, map_size._1, map_size._2)
    }
}

