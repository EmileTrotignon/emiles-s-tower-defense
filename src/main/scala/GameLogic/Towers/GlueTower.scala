package GameLogic.Towers

import java.awt.{Color, Graphics2D}
import Graphics.{Drawing, DrawingElement, Square}

import GameLogic.Monsters.{Monster, MonsterModifier, MonsterVals}
import GameLogic.{Double2, Int2, SizeInfo}

class GlueTower(square_ : Int2, size_info: SizeInfo) extends ModifierTower(square_, size_info)
{
    override val drawing: Drawing = new Drawing(Array(
        DrawingElement(filled_up = true, Color.green, Graphics.Square.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Square.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Square(Double2(0, 0), 0.36)),
        DrawingElement(filled_up = false, Color.black, Graphics.Square(Double2(0, 0), 0.12)),
        DrawingElement(filled_up = false, Color.black, Graphics.Square(Double2(0, 0), 0.04))))

    protected val _slowdown: Double = 0.6

    def slowdown: Double = _slowdown
    
    override def is_applicable(m: Monster): Boolean =
    {
        Double2.dist(m.position, position) <= reach
    }

    case class TModifier() extends MonsterModifier
    {
        override def apply(monster_vals: MonsterVals): Option[MonsterVals] =
        {
            if (is_applicable(monster_vals.monster))
                Some(MonsterVals(
                    monster_vals.position, monster_vals.max_hp, monster_vals.hp,
                    monster_vals.damage, monster_vals.speed * slowdown, monster_vals.direction,
                    monster_vals.loot, monster_vals.size, monster_vals.take_damage, monster_vals.monster))
            else None
        }
    }

    override val modifiers: Set[() => MonsterModifier] = Set[() => MonsterModifier](TModifier)

    override protected val damage: Double = 0
    override protected val period: Int = 0
    override protected val _reach: Double = 4
}
