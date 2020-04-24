package GameLogic.Monsters

import java.awt.Color

import GameLogic.Double2
import Graphics.{Drawing, DrawingElement}

class ShieldMonster(position_ : Double2) extends InfluencingMonster(position_, 3, Color.yellow)
{
    override val _max_hp: Double = 100
    override var _hp: Double = _max_hp
    override val _speed: Double = 0.008
    override val _damage: Double = 1
    override val _loot: Double = 4
    override val _size: Double = 0.5
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.black, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.white, Graphics.Circle.unit),
        DrawingElement(filled_up = true, reach_color, Graphics.Rectangle(Double2(0, 0), Double2(0.2,0.35)))))
 
    case class SModifier() extends MonsterModifier
    {
        override def apply(monster_vals: MonsterVals): Option[MonsterVals] =
        {
            if (is_applicable(monster_vals.monster))
                Some(MonsterVals(
                    monster_vals.position, monster_vals.max_hp, monster_vals.hp,
                    monster_vals.damage, monster_vals.speed, monster_vals.direction,
                    monster_vals.loot, monster_vals.size, damage => _take_damage(2*damage/3), monster_vals.monster))
            else None
        }
    }

    override val modifiers_for_others: Set[() => MonsterModifier] = Set[() => MonsterModifier](SModifier)
}
