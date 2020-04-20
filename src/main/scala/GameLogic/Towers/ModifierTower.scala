package GameLogic.Towers

import GameLogic._
import _root_.GameLogic.Monsters._

abstract class ModifierTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info)
{
    private var tick: Int = 0

    val modifiers: Set[() => MonsterModifier]

    def is_applicable(m: Monster): Boolean

    override def tick(b: BoardLogic): Unit =
    {
        b.monsters.filter(is_applicable).foreach(monster => modifiers.foreach(modifier => monster.add_modifier(modifier())))

        tick += 1
    }
}

