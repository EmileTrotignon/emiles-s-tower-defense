package GameLogic

import java.awt.{Point, Rectangle}

case class Int2(x: Int, y: Int)
{
    def this(p: Point) =
    {
        this(p.x, p.y)
    }

    def /(w: Int): Int2 =
    {
        Int2(x / w, y / w)
    }

    def *(w: Int): Int2 =
    {
        Int2(x * w, y * w)
    }

    def +(v: Int2): Int2 =
    {
        Int2(x + v.x, y + v.y)
    }

    def -(v: Int2): Int2 =
    {
        Int2(x - v.x, y - v.y)
    }

    def squared_dist_to_origin(): Int =
    {
        x * x + y * y
    }

    def dist_to_origin(): Double =
    {
        Math.sqrt(this.squared_dist_to_origin())
    }

    def is_in_bounds(r: Rectangle = new Rectangle(0, 0, 1, 1)): Boolean =
    {
        r.x <= x && r.y <= y && x <= (r.x + r.width) && y <= (r.y + r.height)
    }

    def toDouble2: Double2 =
    {
        Double2(x.toDouble, y.toDouble)
    }
}

case class Double2(x: Double, y: Double)
{

    def +(v: Double2): Double2 =
    {
        Double2(x + v.x, y + v.y)
    }

    def -(v: Double2): Double2 =
    {
        Double2(x - v.x, y - v.y)
    }

    def /(w: Double): Double2 =
    {
        Double2(x / w, y / w)
    }

    def *(w: Double): Double2 =
    {
        Double2(x * w, y * w)
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

    def is_in_bounds(x_ : Double = 0, y_ : Double = 0, w: Double = 1, h: Double = 1): Boolean =
    {
        x_ <= x && y_ <= y && x <= (x_ + w) && y <= (y_ + h)
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

    def random_gaussian(): Double2 =
    {
        Double2(scala.util.Random.nextGaussian(), scala.util.Random.nextGaussian())
    }

    def random(): Double2 =
    {
        Double2(scala.util.Random.nextDouble(), scala.util.Random.nextDouble())
    }

    def random_normalised(): Double2 =
    {
        normalized(random() - Double2(0.5, 0.5))
    }

}
