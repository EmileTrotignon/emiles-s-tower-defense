package GameLogic.Monsters

abstract class MonsterModifier
{
    var marked_for_death: Boolean = false
    
    /* inutile finalement :
    protected var n_tick: Int = 0

    def tick(): Unit =
    {
       n_tick += 1
    }
    */

    def apply(monster_vals: MonsterVals): Option[MonsterVals]

}
