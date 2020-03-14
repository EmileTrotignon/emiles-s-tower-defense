package Graphics

import java.awt.{Color, Graphics2D}

import GameLogic.{Double2, SizeInfoPixels}


case class DrawingElement(filled_up: Boolean, color: Color, shape: Shape)
{
    def paint(size_info: SizeInfoPixels, g: Graphics2D, position: Double2, scale: Double): Unit =
    {
        if (filled_up)
            shape.fill(size_info, g, color, position, scale)
        else
            shape.draw(size_info, g, color, position, scale)
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
                case s => throw new RuntimeException(s"${s} is not a shape")
            }
        DrawingElement(filled_up, color, basic_shape)
    }
}