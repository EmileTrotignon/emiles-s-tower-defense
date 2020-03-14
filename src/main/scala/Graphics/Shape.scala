package Graphics

import java.awt.{Color, Graphics2D}

import GameLogic.{Double2, SizeInfoPixels}

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

}

object Shape
{
    def shape_to_logic(shape_coor: Double2, shape_position: Double2): Double2 =
    {
        (shape_coor / 2) + shape_position
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
}

object Circle
{
    val n_args = 3
    val unit = Circle(Double2(0, 0), 0.5)
}

case class Rectangle(corner: Double2, size: Double2) extends Shape
{
    override def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        super.draw(size_info, g, color, position, scale)
        val corner_pixels = size_info.logic_to_pixels(Shape.shape_to_logic(corner, position))
        val size_pixels = size_info.logic_to_pixels(size * scale)
        g.drawRect(corner_pixels.x, corner_pixels.y, size_pixels.x, size_pixels.y)
    }

    override def fill(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        super.fill(size_info, g, color, position, scale)
        val corner_pixels = size_info.logic_to_pixels(Shape.shape_to_logic(corner, position))
        val size_pixels = size_info.logic_to_pixels(size * scale)
        g.fillRect(corner_pixels.x, corner_pixels.y, size_pixels.x, size_pixels.y)
    }
}

object Rectangle
{
    val n_args = 4
}

case class Square(corner: Double2, length: Double) extends Shape
{
    override def draw(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        Rectangle(corner, Double2(length, length)).draw(size_info, g, color, position, scale)
    }

    override def fill(size_info: SizeInfoPixels, g: Graphics2D, color: Color, position: Double2, scale: Double = 1): Unit =
    {
        Rectangle(corner, Double2(length, length)).fill(size_info, g, color, position, scale)
    }
}

object Square
{
    val n_args = 3

    val unit: Square = Square(Double2(-0.5, -0.5), 1)
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
