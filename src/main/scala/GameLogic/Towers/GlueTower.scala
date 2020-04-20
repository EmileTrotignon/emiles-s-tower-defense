package GameLogic.Towers

import GameLogic.Monsters.{Monster, MonsterModifier, MonsterVals}
import GameLogic.{Double2, Int2, SizeInfo}

class GlueTower(square_ : Int2, size_info: SizeInfo) extends ModifierTower(square_, size_info)
{

    protected val _slowdown: Double = 0.2

    def slowdown: Double = _slowdown

    case class TModifier() extends MonsterModifier
    {
        override def apply(monster_vals: MonsterVals): Option[MonsterVals] =
        {
            if (n_tick >= 2) None
            else
                Some(MonsterVals(
                    monster_vals.position, monster_vals.max_hp, monster_vals.hp,
                    monster_vals.damage, monster_vals.speed * slowdown, monster_vals.direction,
                    monster_vals.loot, monster_vals.size, monster_vals.take_damage, monster_vals.affect_hp))
        }
    }

    override val modifiers: Set[() => MonsterModifier] = Set[() => MonsterModifier](TModifier)

    override def is_applicable(m: Monster): Boolean =
    {
        Double2.dist(m.position, position) <= reach
    }

    override protected val damage: Double = 0
    override protected val period: Int = 0
    override protected val _reach: Double = 4
}
