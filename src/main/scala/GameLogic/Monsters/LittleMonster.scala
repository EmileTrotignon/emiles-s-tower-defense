package GameLogic.Monsters

import java.awt.Color

import GameLogic.Double2
import Graphics.{Drawing, DrawingElement}

class LittleMonster(position_ : Double2) extends InfluencingMonster(position_, 3, Color.yellow)
{
    protected val ratio = 0.6
 
    override val _max_hp: Double = 7
    override var _hp: Double = _max_hp
    override val _speed: Double = 0.018
    override val _damage: Double = 2
    override val _loot: Double = 4
    override val _size: Double = 0.5
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.black, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.white, Graphics.Circle.unit)))

    case class LModifier() extends MonsterModifier
    {
        override def apply(monster_vals: MonsterVals): Option[MonsterVals] =
        {
            if (is_applicable(monster_vals.monster))
                Some(MonsterVals(
                    monster_vals.position, monster_vals.max_hp, monster_vals.hp,
                    monster_vals.damage, _speed, monster_vals.direction,
                    monster_vals.loot, _size*ratio, damage => monster_vals.take_damage(damage/ratio), monster_vals.monster))
            else None                      
        }
    }

    override val modifiers_for_others: Set[() => MonsterModifier] = Set[() => MonsterModifier](LModifier)
}
