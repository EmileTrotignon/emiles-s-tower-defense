package GameLogic.Towers

import java.awt.Color

import Graphics.{Drawing, DrawingElement}
import _root_.GameLogic.Monsters.Monster
import _root_.GameLogic.{Double2, Int2, SizeInfo, _}

class HeatSeekingTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info)
{
    override protected val damage: Double = 0.8
    override protected val period: Int = 50
    override protected val _reach: Double = 7
    val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.darkGray, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.red, Graphics.Square(Double2(0, 0), 0.5)),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))

    private var tick: Int = 0
    private val bullet_speed = 0.025

    protected class TBullet(var __direction: Double2, override protected var target: Option[Monster], override var _position: Double2) extends HeatSeekingBullet
    {
        _direction = __direction
        override protected val damage: Double = HeatSeekingTower.this.damage
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
            val bullet = new TBullet(direction, Some(monster), position)

            b.spawn_bullet(bullet)
        }
    }

}
