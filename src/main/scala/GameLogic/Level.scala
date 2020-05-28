package GameLogic

import GUI.FSignal
import GameLogic.Monsters._

import scala.collection.mutable
import scala.reflect.ClassTag
import scala.util.Random

object RandomPick
{
    def randomPick[T: ClassTag](set: Set[T]): T =
    {
        val i = Random.nextInt(set.size)
        val a = set.toArray[T]
        a(i)
    }

    def randomPickBy[T](set: Set[T], law: T => Double): T =
    {
        var l = set.toList.sortBy(law)
        val total = set.foldLeft(0.0)((acc, e) => acc + law(e));

        val seed = Random.nextDouble() * total

        var acc = law(l.head)
        while (acc < seed)
        {
            l = l.tail
            acc += law(l.head)
        }
        l.head
    }
}

abstract class Level(val name: String, val map: GameMap, val starting_money: Double, val starting_lives: Double)
{
    def start: Unit

    def get_next_wave: Option[Wave]

    def is_finished: Boolean
    
    private var _wave_number: Int = 0
    
    def wave_number: Int = _wave_number
    
    val updated_signal = new FSignal[this.type]
    
    def wave_number_=(new_wave_number: Int)
    {
        _wave_number = new_wave_number
        updated_signal.emit(this)
    }
}

private case class MonsterDesc(constructor: Double2 => Monster, difficulty: Double, value: Double)

class InfiniteLevel(level_name: String, override val map: GameMap, override val starting_money: Double, override val starting_lives: Double, val spawn_tiles: Set[Int2])
  extends Level(level_name, map, starting_money, starting_lives)
{

    private val cyan_monster: Double2 => Monster = new CyanMonster(_)

    private val blue_monster: Double2 => Monster = new BlueMonster(_)
    private val shield_monster: Double2 => Monster = new ShieldMonster(_)
    private val healer_monster: Double2 => Monster = new HealerMonster(_)
    private val protector_monster: Double2 => Monster = new ProtectorMonster(_)
    private val little_monster: Double2 => Monster = new LittleMonster(_)
    private var capital = starting_money

    private val monsters = Set[MonsterDesc](
        MonsterDesc(cyan_monster, 2, 1),
        MonsterDesc(blue_monster, 3, 2),
        MonsterDesc(shield_monster, 8, 4),
        MonsterDesc(healer_monster, 10, 4),
        MonsterDesc(protector_monster, 10, 4),
        MonsterDesc(little_monster, 20, 3),
    )

    private val difficulty_sum = monsters.foldLeft(0.0)((d, m) => d + 1 / m.difficulty)

    override def start: Unit =
    {}


    override def get_next_wave: Option[Wave] =
    {
        val wave_map = mutable.Map[Double2 => Monster, Int]()
        var wave_cost = 0.0
        var wave_return_on_investment = 0.0

        while (wave_cost < capital)
        {
            val monster_desc = RandomPick.randomPickBy(monsters, (m: MonsterDesc) => (if (m.difficulty < capital / 5)
            {
                1 / m.difficulty
            } else 0))
            wave_map.get(monster_desc.constructor) match
            {
                case Some(n) => wave_map.update(monster_desc.constructor, n + 1)
                case None => wave_map.update(monster_desc.constructor, 1)
            }
            wave_cost += monster_desc.difficulty
            wave_return_on_investment += monster_desc.value
        }
        capital += wave_return_on_investment / 2
        wave_number+=1
        Some(new Wave(List(wave_map.toMap), RandomPick.randomPick(spawn_tiles)))

    }

    override def is_finished: Boolean = false
}


object Level
{


    private class LevelFromList(override val name: String, override val map: GameMap, override val starting_money: Double, override val starting_lives: Double, val waves: List[Wave])
      extends Level(name, map, starting_money, starting_lives)
    {
        var next_waves: List[GameLogic.Wave] = Nil

        override def is_finished: Boolean = next_waves == Nil

        override def start: Unit =
        {
            next_waves = waves
        }

        override def get_next_wave: Option[Wave] =
        {
            next_waves match
            {
                case wave :: next =>
                    next_waves = next
                    wave_number += 1
                    Some(wave)
                case Nil => None
            }
        }
    }

    def from_list(name: String, map: GameMap, starting_money: Double, starting_lives: Double, waves: List[Wave]): Level =
    {
        new LevelFromList(name, map, starting_money, starting_lives, waves)
    }
}