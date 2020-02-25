package GameLogic

import java.awt.{Color, Graphics2D}

abstract class Bullet(var position: Double2, val damage: Double, val direction_and_speed: Double2) extends BoardObject
{

    override def tick(b: BoardLogic): Unit =
    {
        position = position + direction_and_speed
    }

    def is_colliding(monster: Monster): Boolean =
    {
        Double2.dist(this.position, monster.position) <= monster.size / 2
    }
}

case class BaseBullet(position_ : Double2, direction: Double2)
  extends Bullet(position_, 1, Double2.normalized(direction) * 0.007)
{

    val size = 0.01

    override def paint(size_info: SizeInfo, g: Graphics2D): Unit =
    {
        g.setColor(Color.YELLOW)
        val pos_pixels = size_info.logic_to_pixels(position)
        val size_pixels = size_info.logic_to_pixels(new Double2(size, size))
        g.fillOval(pos_pixels.x - size_pixels.x / 2, pos_pixels.y - size_pixels.y / 2, size_pixels.x, size_pixels.y)
    }
}
