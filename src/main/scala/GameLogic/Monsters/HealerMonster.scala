package GameLogic.Monsters

import java.awt.Color

import GameLogic.Double2
import Graphics.{Drawing, DrawingElement}

class HealerMonster(position_ : Double2) extends ModifierMonster(position_)
{
    protected val reach = 3.5
 
    override val _max_hp: Double = 7
    override var _hp: Double = _max_hp
    override val _speed: Double = 0.008
    override val _damage: Double = 1
    override val _loot: Double = 3
    override val _size: Double = 0.5
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.white, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.white, Graphics.Circle(Double2(0, 0), reach))))
    
    override def is_applicable(m: Monster): Boolean =
    {
        Double2.dist(m.position, position) <= reach
    }
 
    case class HModifier() extends MonsterModifier
    {
        override def apply(monster_vals: MonsterVals): Option[MonsterVals] =
        {
            if (monster_vals.hp + 1 <= monster_vals.max_hp && scala.util.Random.nextDouble() < 0.005)
            {
              monster_vals.affect_hp(monster_vals.hp + 0.5)
            }
            None    
        }
    }

    override val modifiers_for_others: Set[() => MonsterModifier] = Set[() => MonsterModifier](HModifier)
}
