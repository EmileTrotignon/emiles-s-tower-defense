package Graphics

import java.awt.{Color, Graphics2D}

import GameLogic.{Double2, Int2, SizeInfo}

object LifeBar
{
    private val life_bar_width = 8

    def draw_life_bar(pos: Double2, size: Double, size_info: SizeInfo, hp: Double, max_hp: Double, g: Graphics2D): Unit =
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
}
