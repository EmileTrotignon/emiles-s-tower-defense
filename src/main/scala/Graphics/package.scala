import java.awt.{Color, Graphics2D}

import GameLogic.{Double2, SizeInfoPixels}

package object Graphics
{
    private val life_bar_width = 8

    def draw_life_bar(pos: Double2, size: Double, size_info: SizeInfoPixels, hp: Double, max_hp: Double, g: Graphics2D): Unit =
    {
        val corner = size_info.logic_to_pixels(pos + Double2(-size / 2, -size))
        val length = size_info.logic_to_pixels(Double2(size, 0)).x
        g.setColor(Color.red)
        g.fillRect(corner.x, corner.y, length, life_bar_width)
        g.setColor(Color.green)
        g.fillRect(corner.x, corner.y, (length * hp / max_hp).round.toInt, life_bar_width)
        g.setColor(Color.black)
        g.drawRect(corner.x, corner.y, length, life_bar_width)
    }

    def fill_oval(size_info: SizeInfoPixels, size: Double2, position: Double2, g: Graphics2D): Unit =
    {
        val pos_pixels = size_info.logic_to_pixels(position)

        val size_pixels = size_info.logic_to_pixels(size)
        g.fillOval(pos_pixels.x - size_pixels.x / 2, pos_pixels.y - size_pixels.y / 2, size_pixels.x, size_pixels.y)

    }

    def draw_oval(size_info: SizeInfoPixels, size: Double2, position: Double2, g: Graphics2D): Unit =
    {
        val pos_pixels = size_info.logic_to_pixels(position)

        val size_pixels = size_info.logic_to_pixels(size)
        g.drawOval(pos_pixels.x - size_pixels.x / 2, pos_pixels.y - size_pixels.y / 2, size_pixels.x, size_pixels.y)

    }

    def draw_line(size_info: SizeInfoPixels, p1: Double2, p2: Double2, g: Graphics2D): Unit =
    {
        val p1_pixels = size_info.logic_to_pixels(p1)
        val p2_pixels = size_info.logic_to_pixels(p2)
        g.drawLine(p1_pixels.x, p1_pixels.y, p2_pixels.x, p2_pixels.y)
    }

    def fill_oval_countour(size_info: SizeInfoPixels, size: Double2, position: Double2, g: Graphics2D,
                           color_inside: Color, color_countour: Color): Unit =
    {
        g.setColor(color_inside)
        fill_oval(size_info: SizeInfoPixels, size: Double2, position: Double2, g: Graphics2D)
        g.setColor(color_countour)
        draw_oval(size_info: SizeInfoPixels, size: Double2, position: Double2, g: Graphics2D)

    }
}
