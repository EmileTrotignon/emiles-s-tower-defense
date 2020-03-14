package GameLogic.Towers

import java.awt.Color

import GameLogic._
import Graphics.{Drawing, DrawingElement, Square}

case class SquareTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info: SizeInfo)
{

    protected val damage: Double = 0.5
    protected val period: Int = 20
    protected val _reach: Double = 2
    protected val bullet_speed = 0.07
    override val drawing: Drawing = new Drawing(Array(DrawingElement(filled_up = true, Color.green, Square.unit)))

    private var tick: Int = 0

    protected class TBullet(override var position: Double2, val direction: Double2) extends Bullet
    {
        val speed: Double = bullet_speed
        override protected val damage: Double = SquareTower.this.damage
        override val direction_and_speed: Double2 = Double2.normalized(direction) * speed
        override protected val size: Double = 0.1
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