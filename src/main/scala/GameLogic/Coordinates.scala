package GameLogic

class Coordinates(val x: Int, val y: Int)
{
    def this(other: Coordinates) =
    {
        this(other.x, other.y)
    }

    def +(v: Coordinates): Coordinates =
    {
        new Coordinates(x + v.x, y + v.y)
    }

    def -(v: Coordinates): Coordinates =
    {
        new Coordinates(x - v.x, y - v.y)
    }

    def squarred_dist_to_origin(): Int =
    {
        x * x + y * y
    }

    def dist_to_origin(): Double =
    {
        Math.sqrt(this.squarred_dist_to_origin())
    }
}

object Coordinates
{
    def dist(p1: Coordinates, p2: Coordinates): Double =
    {
        (p1 - new Vector(p2.x, p2.y)).dist_to_origin()
    }

    def squarred_dist(p1: Coordinates, p2: Coordinates): Int =
    {
        (p1 - new Vector(p2.x, p2.y)).squarred_dist_to_origin()
    }
}

class Point(coordinates: Coordinates) extends Coordinates(coordinates)
{
    //coordinates are expressed in pixels
    def this(x_ : Int, y_ : Int) =
    {
        this(new Coordinates(x_, y_))
    }
}

class Square(coordinates: Coordinates) extends Coordinates(coordinates)
{
    //coordinates represents a cell on the grid
    def this(x_ : Int, y_ : Int) =
    {
        this(new Coordinates(x_, y_))
    }
}

class Vector(coordinates: Coordinates) extends Coordinates(coordinates: Coordinates)
{
    //coordinates represent a vector
    def this(x_ : Int, y_ : Int) =
    {
        this(new Coordinates(x_, y_))
    }
}
