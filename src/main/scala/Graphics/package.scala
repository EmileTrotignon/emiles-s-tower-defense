import java.awt.{Color, Graphics2D}

import GameLogic.{Double2, SizeInfoPixels}

package object Graphics
{
    private val life_bar_width = 5

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

    def draw_line(size_info: SizeInfoPixels, p1: Double2, p2: Double2, g: Graphics2D): Unit =
    {
        val p1_pixels = size_info.logic_to_pixels(p1)
        val p2_pixels = size_info.logic_to_pixels(p2)
        g.drawLine(p1_pixels.x, p1_pixels.y, p2_pixels.x, p2_pixels.y)
    }

}
