package GameLogic

import java.awt.Rectangle

class Point2DInt(val x: Int, val y: Int)
{
    def +(v: Point2DInt): Point2DInt =
    {
        new Point2DInt(x + v.x, y + v.y)
    }

    def -(v: Point2DInt): Point2DInt =
    {
        new Point2DInt(x - v.x, y - v.y)
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

object Point2DInt
{
    def dist(p1: Point2DInt, p2: Point2DInt): Double =
    {
        (p1 - new Point2DInt(p2.x, p2.y)).dist_to_origin()
    }

    def squared_dist(p1: Point2DInt, p2: Point2DInt): Int =
    {
        (p1 - new Point2DInt(p2.x, p2.y)).squared_dist_to_origin()
    }

    def pixels_to_squares(p: Point2DInt, pixel_width: Int, pixel_height: Int, square_width: Int, square_height: Int): Point2DInt =
    {
        Point2DDouble.floor(new Point2DDouble(p.x.toDouble * square_width.toDouble / pixel_width.toDouble,
            p.y.toDouble * square_height.toDouble / pixel_height.toDouble))
    }

    def square_corner(p: Point2DInt, square_width: Int, square_height: Int): Point2DDouble =
    {
        new Point2DDouble(p.x.toDouble / square_width.toDouble, p.y.toDouble / square_height.toDouble)
    }

    def square_center(p: Point2DInt, square_width: Int, square_height: Int): Point2DDouble =
    {
        new Point2DDouble((p.x.toDouble + 0.5) / square_width.toDouble, (p.y.toDouble + 0.5) / square_height.toDouble)
    }


}

class Point2DDouble(val x: Double, val y: Double)
{
    def this(other: Point2DDouble) =
    {
        this(other.x, other.y)
    }

    def +(v: Point2DDouble): Point2DDouble =
    {
        new Point2DDouble(x + v.x, y + v.y)
    }

    def -(v: Point2DDouble): Point2DDouble =
    {
        new Point2DDouble(x - v.x, y - v.y)
    }

    def squared_dist_to_origin(): Double =
    {
        x * x + y * y
    }

    def dist_to_origin(): Double =
    {
        Math.sqrt(this.squared_dist_to_origin())
    }

    def to_pixels(width: Int, height: Int): Point2DInt =
    {
        Point2DDouble.round(new Point2DDouble(x * width, y * height))
    }

    def to_square(width: Int, height: Int): Point2DInt =
    {
        Point2DDouble.floor(new Point2DDouble(x * width, y * height))
    }

    def is_in_bounds(r: Rectangle=new Rectangle(0, 0, 1, 1)): Boolean =
    {
        r.x <= x && r.y <= y && x <= (r.x + r.width) && y <= (r.y + r.height)
    }
}

object Point2DDouble
{

    def floor(p: Point2DDouble): Point2DInt =
    {
        new Point2DInt(math.floor(p.x).toInt, math.floor(p.y).toInt)
    }

    def round(p: Point2DDouble): Point2DInt =
    {
        new Point2DInt(math.round(p.x).toInt, math.round(p.y).toInt)
    }

    def dist(p1: Point2DDouble, p2: Point2DDouble): Double =
    {
        (p1 - new Point2DDouble(p2.x, p2.y)).dist_to_origin()
    }

    def squared_dist(p1: Point2DDouble, p2: Point2DDouble): Double =
    {
        (p1 - new Point2DDouble(p2.x, p2.y)).squared_dist_to_origin()
    }


}
