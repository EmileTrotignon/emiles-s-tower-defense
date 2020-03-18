package GameLogic.Monsters

import java.awt.Color

import GameLogic.Double2
import Graphics.{Drawing, DrawingElement}

class BlueMonster(position_ : Double2) extends Monster(position_)
{
    override val _max_hp: Double = 12
    override var _hp: Double = _max_hp
    override val _speed: Double = 0.012
    override val _damage: Double = 4
    override val _loot: Double = 2
    override val _size: Double = 0.5
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.blue, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))


}
