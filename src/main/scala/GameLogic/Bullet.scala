package GameLogic

import java.awt.{Color, Graphics2D}

abstract class Bullet(var position: Point2DDouble, val damage: Double, val direction_and_speed: Point2DDouble) extends BoardObject
{

    override def tick(b: BoardLogic): Unit =
    {
        position = new Point2DDouble(position + direction_and_speed)
    }

    def is_colliding(monster: Monster): Boolean =
    {
        Point2DDouble.dist(this.position, monster.position) <= monster.size
    }
}

case class BaseBullet(position_ : Point2DDouble, direction: Point2DDouble)
  extends Bullet(position_, 1, Point2DDouble.normalized(direction) * 0.01)
{
    override def paint(g: Graphics2D): Unit =
    {
        val pos_pixels = position.to_pixels(g.getClipBounds().width, g.getClipBounds().height)
        g.setColor(Color.YELLOW)
        g.fillRect(pos_pixels.x, pos_pixels.y, 10, 10)
    }
}
