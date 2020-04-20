package GameLogic.Monsters

import java.awt.Color
import scala.collection.mutable

import GameLogic._
import Graphics.{Drawing, DrawingElement}

abstract class ModifierMonster(position_ : Double2) extends Monster(position_)
{   
    private var tick: Int = 0

    val modifiers_for_others: Set[() => MonsterModifier]

    def is_applicable(m: Monster): Boolean
    
    override def tick(b: BoardLogic): Unit =
    {
        b.monsters.filter(is_applicable).foreach(monster => modifiers_for_others.foreach(modifier => monster.add_modifier(modifier())))

        tick += 1
        
        super.tick(b)
    }
}


