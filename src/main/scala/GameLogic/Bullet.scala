package GameLogic

import java.awt.{Color, Graphics2D}

import scala.collection.mutable

abstract class Bullet(var position: Double2, protected val damage: Double, val direction_and_speed: Double2) extends BoardObject
{

    protected val size: Double

    override def tick(b: BoardLogic): Unit =
    {
        position = position + direction_and_speed
    }

    def is_colliding(monster: Monster): Boolean =
    {
        Double2.dist(this.position, monster.position) <= (monster.size + size) / 2
    }

    def make_damage(monster: Monster): Unit =
    {
        monster.take_damage(damage)
    }
}

case class BaseBullet(position_ : Double2, direction: Double2)
  extends Bullet(position_, 1, Double2.normalized(direction) * 0.007)
{

    protected val size = 0.01

    override def paint(size_info: SizeInfo, g: Graphics2D): Unit =
    {
        g.setColor(Color.YELLOW)
        Graphics.draw_oval(size_info, Double2(size, size), position, g)
    }
}

case class BigBullet(monsters: mutable.Set[Monster], position_ : Double2, direction: Double2)
  extends Bullet(position_, 1, Double2.normalized(direction) * 0.005)
{

    protected val size = 0.03
    protected val zone_size = 0.1
    protected val zone_damage: Double = damage / 2

    override def paint(size_info: SizeInfo, g: Graphics2D): Unit =
    {
        g.setColor(Color.YELLOW)
        Graphics.draw_oval(size_info, Double2(size, size), position, g)
    }

    override def make_damage(monster: Monster): Unit =
    {
        super.make_damage(monster)
        monsters.filter(m => (Double2.dist(position, m.position) <= zone_size)).foreach(m =>
        {
            m.take_damage(zone_damage)
        })
    }
}
