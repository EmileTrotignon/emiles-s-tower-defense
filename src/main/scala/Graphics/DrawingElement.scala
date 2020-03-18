package Graphics

import java.awt.{Color, Graphics2D}

import GameLogic.{Double2, SizeInfoPixels}


case class DrawingElement(filled_up: Boolean, color: Color, shape: Shape)
{
    def paint(size_info: SizeInfoPixels, g: Graphics2D, position: Double2, scale: Double, angle: Double = 0): Unit =
    {
        if (angle == 0)
        {
            if (filled_up)
                shape.fill(size_info, g, color, position, scale)
            else
                shape.draw(size_info, g, color, position, scale)
        }
        else
        {
            if (filled_up)
                shape.rotated(angle).fill(size_info, g, color, position, scale)
            else
                shape.rotated(angle).draw(size_info, g, color, position, scale)
        }
    }
}

object DrawingElement
{

    val n_args = 3

    private def color_from_string(s: String): Color =
    {
        Color.black
    }

    def from_string(s: String): Unit =
    {
        val words = s.split(" ")
        assert(words.length >= 3)

        val filled_up: Boolean = words(0) match
        {
            case "fill" => true
            case "draw" => false
            case s => throw new RuntimeException(s"Invalid token $s")
        }

        val color = color_from_string(words(1))

        val basic_shape: Shape =
            Shape.from_words(words)
        DrawingElement(filled_up, color, basic_shape)
    }
}