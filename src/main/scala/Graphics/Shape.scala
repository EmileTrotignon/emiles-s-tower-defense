package Graphics

import java.awt.{Color, Graphics2D}

import GameLogic.{Double2, SizeInfoPixels}
import Graphics.DrawingElement.n_args

import scala.collection.mutable.ArrayBuffer

abstract class Shape()
{
    /**
     * Shape coordinates are between -0.5 and 0.5. Shapes also do not have a position in the logic space.
     * All sizes must be relative to the size of the other element in the Drawing, since scale is here to account for logical size.
     **/

    def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        g.setColor(color)
    }

    def fill(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        g.setColor(color)
    }

    def rotated(angle: Double): Shape

}

object Shape
{
    def shape_to_logic(shape_coor: Double2, shape_position: Double2): Double2 =
    {
        (shape_coor / 2) + shape_position
    }

    def from_words(words: Array[String]): Shape =
    {
        words(2) match
        {
            case "circle" =>
                if (words.length != n_args + Circle.n_args)
                    throw new RuntimeException(s"Circle shape needs ${Circle.n_args} arguments, ${words.length - n_args} provided")
                Circle(Double2(words(4).toDouble, words(5).toDouble), words(6).toDouble)

            case "rectangle" =>
                if (words.length != n_args + Rectangle.n_args)
                    throw new RuntimeException(s"Rectangle shape needs ${Rectangle.n_args} arguments, ${words.length - n_args} provided")
                Rectangle(Double2(words(4).toDouble, words(5).toDouble), Double2(words(6).toDouble, words(7).toDouble))

            case "square" =>
                if (words.length != n_args + Square.n_args)
                    throw new RuntimeException(s"Square shape needs ${Square.n_args} arguments, ${words.length - n_args} provided")
                Square(Double2(words(4).toDouble, words(5).toDouble), words(6).toDouble)

            case "polygon" =>
                if (words.length < n_args + Polygon.min_args)
                    throw new RuntimeException(s"Polygon shape needs at least ${Polygon.min_args} arguments, ${words.length - n_args} provided")
                if ((words.length - n_args) % 2 != 0)
                    throw new RuntimeException(s"Polygon shape needs an even number of arguments, ${words.length - n_args} provided")
                Polygon.from_double_array(words.slice(4, words.length).map(s => s.toDouble))

            case "line" =>
                if (words.length != n_args + Line.n_args)
                    throw new RuntimeException(s"Line shape needs ${Line.n_args} arguments, ${words.length - n_args} provided")
                Line(Double2(words(4).toDouble, words(5).toDouble), Double2(words(6).toDouble, words(7).toDouble))

            case s => throw new RuntimeException(s"'${s}' is not a shape")
        }
    }

}

case class Oval(center: Double2, size: Double2) extends Shape
{
    override def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        super.draw(size_info, g, color, position, scale)
        val center_pixels = size_info.logic_to_pixels(Shape.shape_to_logic(center, position))
        val size_pixels = size_info.logic_to_pixels(size * scale)
        g.drawOval(center_pixels.x - size_pixels.x / 2, center_pixels.y - size_pixels.y / 2, size_pixels.x, size_pixels.y)
    }

    override def fill(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        super.fill(size_info, g, color, position, scale)
        val center_pixels = size_info.logic_to_pixels(Shape.shape_to_logic(center, position))
        val size_pixels = size_info.logic_to_pixels(size * scale)
        g.fillOval(center_pixels.x - size_pixels.x / 2, center_pixels.y - size_pixels.y / 2, size_pixels.x, size_pixels.y)
    }

    override def rotated(angle: Double): Oval =
    {
        Oval(center.rotated(angle), size)
    }
}

case class Circle(center: Double2, radius: Double) extends Shape
{
    override def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        Oval(center, Double2(radius * 2, radius * 2)).draw(size_info, g, color, position, scale)
    }

    override def fill(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        Oval(center, Double2(radius * 2, radius * 2)).fill(size_info, g, color, position, scale)
    }

    override def rotated(angle: Double): Circle =
    {
        Circle(center.rotated(angle), radius)
    }
}

object Circle
{
    val n_args = 3
    val unit: Circle = Circle(Double2(0, 0), 0.5)
}

case class Rectangle(center: Double2, size: Double2) extends Shape
{
    override def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        super.draw(size_info, g, color, position, scale)
        val corner_pixels = size_info.logic_to_pixels(Shape.shape_to_logic(center - size / 2, position))
        val size_pixels = size_info.logic_to_pixels(size * scale)
        g.drawRect(corner_pixels.x, corner_pixels.y, size_pixels.x, size_pixels.y)
    }

    override def fill(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        super.fill(size_info, g, color, position, scale)
        val corner_pixels = size_info.logic_to_pixels(Shape.shape_to_logic(center - size / 2, position))
        val size_pixels = size_info.logic_to_pixels(size * scale)
        g.fillRect(corner_pixels.x, corner_pixels.y, size_pixels.x, size_pixels.y)
    }

    override def rotated(angle: Double): Rectangle =
    {
        Rectangle(center.rotated(angle), size)
    }
}

object Rectangle
{
    val n_args = 4
}

case class Square(center: Double2, size: Double) extends Shape
{
    override def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        Rectangle(center, Double2(size, size)).draw(size_info, g, color, position, scale)
    }

    override def fill(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        Rectangle(center, Double2(size, size)).fill(size_info, g, color, position, scale)
    }

    override def rotated(angle: Double): Square =
    {
        Square(center.rotated(angle), size)
    }
}

object Square
{
    val n_args = 3

    val unit: Square = Square(Double2(0, 0), 1)
}

case class Polygon(vertices: Array[Double2]) extends Shape
{
    override def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double): Unit =
    {
        super.draw(size_info, g, color, position, scale)
        assert(false)
    }

    override def fill(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double): Unit =
    {
        super.fill(size_info, g, color, position, scale)
        assert(false)
    }

    override def rotated(angle: Double): Shape =
    {
        Polygon(vertices.map(v => v.rotated(angle)))
    }
}


case class Line(origin: Double2, size: Double2) extends Shape
{
    override def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double): Unit =
    {
        super.draw(size_info, g, color, position, scale)
        val pos_pixels = size_info.logic_to_pixels(Shape.shape_to_logic(origin, position))
        val size_pixels = size_info.logic_to_pixels(size * scale)
        g.drawLine(pos_pixels.x, pos_pixels.y, size_pixels.x, size_pixels.y)
    }

    override def rotated(angle: Double): Line =
    {
        Line(origin.rotated(angle), size)
    }
}

case class Vector(origin: Double2, arrival: Double2) extends Shape
{
    override def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double): Unit =
    {
        super.draw(size_info, g, color, position, scale)
        Line(origin, arrival - origin).draw(size_info, g, color, position, scale)
    }

    override def rotated(angle: Double): Vector =
    {
        Vector(origin.rotated(angle), arrival.rotated(angle))
    }
}

object Line
{
    val n_args = 4
}

object Polygon
{
    val min_args = 3

    def from_double_array(array: Array[Double]): Polygon =
    {
        assert(array.length % 2 == 0 && array.length >= 3)
        val buffer_array: ArrayBuffer[Double2] = new ArrayBuffer[Double2]()
        var i = 0;
        while (i < array.length)
        {
            buffer_array.addOne(Double2(array(i), array(i + 1)))
            i += 2
        }

        Polygon(buffer_array.toArray)
    }
}
