package GameLogic.Towers

import java.awt.{Color, Graphics2D}
import Graphics.{Drawing, DrawingElement, Square}

import GameLogic._
import GameLogic.Monsters.{Monster, MonsterModifier, MonsterVals}
import GameLogic.{Double2, Int2, SizeInfo}

class SquareTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info: SizeInfo)
{
    protected val damage: Double = 0.5
    protected val period: Int = 20
    protected val _reach: Double = 2
    protected val bullet_speed = 0.07
    override val drawing: Drawing = new Drawing(Array(DrawingElement(filled_up = true, Color.green, Square.unit)))

    private var tick: Int = 0

    protected class TBullet(override var _position: Double2, var __direction: Double2) extends Bullet
    {
        _direction = __direction
        override val speed: Double = bullet_speed
        override protected val damage: Double = SquareTower.this.damage
        override protected val _size: Double = 0.1
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
            val bullet = new TBullet(position, direction)
            b.spawn_bullet(bullet)
        }
    }
}
