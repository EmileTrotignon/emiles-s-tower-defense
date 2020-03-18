package GameLogic

import java.awt.{Color, Graphics2D}

import GameLogic.Monsters.Monster
import Graphics.{Drawing, DrawingElement}

import scala.collection.mutable

abstract class Bullet() extends BoardObject
{
    protected val damage: Double
    protected val speed: Double

    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.yellow, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))

    override def tick(b: BoardLogic): Unit =
    {
        _position = position + direction * speed
    }

    def is_colliding(monster: Monster): Boolean =
    {
        Double2.dist(this.position, monster.position) <= (monster.size + size) / 2
    }

    def make_damage(monster: Monster): Unit =
    {
        monster.take_damage(damage)
    }

    override def paint(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        layer match
        {
            case Layers.bullets =>
                super.paint(size_info, layer, g)
            case _ => ()
        }

    }
}

abstract class ZoneDamageBullet
  extends Bullet
{
    protected val monsters: mutable.Set[Monster]
    protected val zone_size: Double
    protected val zone_damage: Double


    override def make_damage(monster: Monster): Unit =
    {
        super.make_damage(monster)
        monsters.filter(m => (Double2.dist(_position, m.position) <= zone_size)).foreach(m =>
        {
            m.take_damage(zone_damage)
        })
    }
}
