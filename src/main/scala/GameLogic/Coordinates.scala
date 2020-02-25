package GameLogic

import java.awt.Rectangle

case class Int2(x: Int, y: Int)
{
    def +(v: Int2): Int2 =
    {
        new Int2(x + v.x, y + v.y)
    }

    def -(v: Int2): Int2 =
    {
        new Int2(x - v.x, y - v.y)
    }

    def squared_dist_to_origin(): Int =
    {
        x * x + y * y
    }

    def dist_to_origin(): Double =
    {
        Math.sqrt(this.squared_dist_to_origin())
    }
}

case class Double2(x: Double, y: Double)
{


    def +(v: Double2): Double2 =
    {
        new Double2(x + v.x, y + v.y)
    }

    def -(v: Double2): Double2 =
    {
        new Double2(x - v.x, y - v.y)
    }

    def /(w: Double): Double2 =
    {
        new Double2(x / w, y / w)
    }

    def *(w: Double): Double2 =
    {
        new Double2(x * w, y * w)
    }

    def squared_dist_to_origin(): Double =
    {
        x * x + y * y
    }

    def dist_to_origin(): Double =
    {
        Math.sqrt(this.squared_dist_to_origin())
    }

    def to_pixels(width: Int, height: Int): Int2 =
    {
        Double2.round(new Double2(x * width, y * height))
    }

    def to_square(width: Int, height: Int): Int2 =
    {
        Double2.floor(new Double2(x * width, y * height))
    }

    def is_in_bounds(r: Rectangle = new Rectangle(0, 0, 1, 1)): Boolean =
    {
        r.x <= x && r.y <= y && x <= (r.x + r.width) && y <= (r.y + r.height)
    }
}

object Int2
{
    def dist(p1: Int2, p2: Int2): Double =
    {
        (p1 - new Int2(p2.x, p2.y)).dist_to_origin()
    }

    def squared_dist(p1: Int2, p2: Int2): Int =
    {
        (p1 - new Int2(p2.x, p2.y)).squared_dist_to_origin()
    }

    def pixels_to_squares(p: Int2, pixel_width: Int, pixel_height: Int, square_width: Int, square_height: Int): Int2 =
    {
        Double2.floor(Double2(p.x.toDouble * square_width.toDouble / pixel_width.toDouble,
            p.y.toDouble * square_height.toDouble / pixel_height.toDouble))
    }

    def square_corner(p: Int2, square_width: Int, square_height: Int): Double2 =
    {
        Double2(p.x.toDouble / square_width.toDouble, p.y.toDouble / square_height.toDouble)
    }

    def square_center(p: Int2, square_width: Int, square_height: Int): Double2 =
    {
        Double2((p.x.toDouble + 0.5) / square_width.toDouble, (p.y.toDouble + 0.5) / square_height.toDouble)
    }


}


object Double2
{

    def floor(p: Double2): Int2 =
    {
        new Int2(math.floor(p.x).toInt, math.floor(p.y).toInt)
    }

    def round(p: Double2): Int2 =
    {
        new Int2(math.round(p.x).toInt, math.round(p.y).toInt)
    }

    def dist(p1: Double2, p2: Double2): Double =
    {
        (p1 - new Double2(p2.x, p2.y)).dist_to_origin()
    }

    def squared_dist(p1: Double2, p2: Double2): Double =
    {
        (p1 - new Double2(p2.x, p2.y)).squared_dist_to_origin()
    }

    def normalized(p: Double2): Double2 =
    {
        p / p.dist_to_origin()
    }


}
