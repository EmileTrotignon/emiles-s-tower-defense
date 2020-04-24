package GameLogic.Monsters

import java.awt.Color

import GameLogic.Double2
import Graphics.{Drawing, DrawingElement}

class HealerMonster(position_ : Double2) extends InfluencingMonster(position_, 3, Color.white)
{
    override val _max_hp: Double = 5
    override var _hp: Double = _max_hp
    override val _speed: Double = 0.008
    override val _damage: Double = 1
    override val _loot: Double = 3
    override val _size: Double = 0.5
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, reach_color, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))  

    case class HModifier() extends MonsterModifier
    {
        override def apply(monster_vals: MonsterVals): Option[MonsterVals] =
        {
            if (monster_vals.hp + 0.5 <= monster_vals.max_hp && scala.util.Random.nextDouble() < 0.013)
            {
              monster_vals.monster._hp += 0.5
            }
            None    
        }
    }

    override val modifiers_for_others: Set[() => MonsterModifier] = Set[() => MonsterModifier](HModifier)
}
