package GameLogic.Monsters

import scala.collection.mutable

class MonsterModifierSet(first_values: MonsterVals)
{
    val modifiers: mutable.Set[MonsterModifier] = mutable.Set[MonsterModifier]()

    protected var _values: MonsterVals = first_values

    def values: MonsterVals = _values

    def update(m: Monster): Unit =
    {
        val raw_vals = m.get_raw_vals
        _values = modifiers.foldLeft(raw_vals)((old_val, modifier) =>
        {
            modifier(old_val) match
            {
                case Some(v) => v
                case None =>
                    modifier.marked_for_death = true
                    old_val
            }
        })
        modifiers.filterInPlace(m => !m.marked_for_death)
    }


    def tick(m: Monster): Unit =
    {
        update(m)
        modifiers.foreach(m => m.tick())
    }
}