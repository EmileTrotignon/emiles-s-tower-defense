package GameLogic.Monsters

import java.awt.Color

import GameLogic.Double2
import Graphics.{Drawing, DrawingElement}

class ProtectorMonster(position_ : Double2) extends InfluencingMonster(position_, 4.5, Color.orange)
{
    override val _max_hp: Double = 2
    override var _hp: Double = _max_hp
    override val _speed: Double = 0.010
    override val _damage: Double = 1
    override val _loot: Double = 1
    override val _size: Double = 0.5
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, reach_color, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))

    case class PModifier() extends MonsterModifier
    {
        override def apply(monster_vals: MonsterVals): Option[MonsterVals] =
        {
            if (is_applicable(monster_vals.monster))
                Some(MonsterVals(
                    monster_vals.position, monster_vals.max_hp, monster_vals.hp,
                    monster_vals.damage, monster_vals.speed, monster_vals.direction,
                    monster_vals.loot, monster_vals.size,
                    damage => monster_vals.take_damage(if (damage <= 0.2) {damage/2} else {scala.math.max(damage - 1, 0.2)}),
                    monster_vals.monster))
            else None
        }
    }

    override val modifiers_for_others: Set[() => MonsterModifier] = Set[() => MonsterModifier](PModifier)        
}
