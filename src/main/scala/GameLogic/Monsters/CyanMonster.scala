package GameLogic.Monsters

import java.awt.Color

import GameLogic.Double2
import Graphics.{Circle, Drawing, DrawingElement}

class CyanMonster(position_ : Double2) extends Monster(position_)
{
    override val _max_hp: Double = 10
    override var _hp: Double = _max_hp
    override val _speed: Double = 0.010
    override val _damage: Double = 2
    override val _loot: Double = 1
    override val _size: Double = 0.5
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.cyan, Graphics.Circle.unit),
        DrawingElement(filled_up = true, Color.red, Circle(Double2(0.25, 0), 0.25)),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))
}
