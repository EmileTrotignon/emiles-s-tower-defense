package GameLogic.Towers

import java.awt.Color

import GameLogic._
import Graphics.{Circle, Drawing, DrawingElement}

class SprayTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info: SizeInfo)
{
    protected val damage: Double = 0.5
    protected val period: Int = 6
    protected val _reach: Double = 2 //n'a aucune influence
    protected val bullet_speed = 0.07
    override val drawing: Drawing = new Drawing(Array(DrawingElement(filled_up = true, Color.green, Circle.unit),
        DrawingElement(filled_up = true, Color.yellow, Circle(Double2(0.25, 0), 0.25))))
    //override protected var _direction: Double2 = Double2(1, 0)
    private var tick: Int = 0
    protected var rotation_speed = 0.05;

    protected class TBullet(override var _position: Double2, var __direction: Double2) extends Bullet
    {
        _direction = __direction
        override val speed: Double = bullet_speed
        override protected val damage: Double = SprayTower.this.damage
        override protected val _size: Double = 0.1
    }


    override def tick(b: BoardLogic): Unit =
    {
        if (tick % period == 0 && b.monsters.nonEmpty)
        {
            shoot_bullet(b)
        }
        _direction = _direction.add_angle(rotation_speed)
        tick += 1
    }

    def shoot_bullet(b: BoardLogic): Unit =
    {
        val bullet = new TBullet(position, direction)
        b.spawn_bullet(bullet)
    }
}
