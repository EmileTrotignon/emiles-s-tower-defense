package GameLogic.Towers

import java.awt.Color

import GameLogic._
import _root_.GameLogic.Monsters.Monster
import _root_.GameLogic.{Double2, Int2, SizeInfo}
import Graphics.{Drawing, DrawingElement}

import scala.collection.mutable

class RoundTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info)
{
    override protected val damage: Double = 1
    override protected val period: Int = 120
    override protected val _reach: Double = 5
    val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.green, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))

    private var tick: Int = 0
    private val bullet_speed = 0.015

    protected class TBullet(var __direction: Double2, override protected val monsters: mutable.Set[Monster], override var _position: Double2) extends ZoneDamageBullet
    {
        _direction = __direction
        override val zone_size: Double = 2
        override protected val damage: Double = 0
        override protected val zone_damage: Double = RoundTower.this.damage
        override val speed: Double = bullet_speed
        override val _size: Double = 0.3
    }

    override def tick(b: BoardLogic): Unit =
    {
        if (tick % period == 0 && b.monsters.nonEmpty)
        {
            shoot_bullet(b)
        }
        tick += 1
    }

    def shoot_bullet(b: BoardLogic): Unit =
    {
        val monster = b.monsters.minBy(m => Double2.squared_dist(position, m.position))
        if (Double2.dist(monster.position, position) <= _reach)
        {
            val direction = Double2.normalized(monster.position - position)
            val bullet = new TBullet(direction, b.monsters, position)

            b.spawn_bullet(bullet)
        }
    }
}
