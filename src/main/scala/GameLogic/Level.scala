package GameLogic

abstract class Level(val name: String, val map: GameMap, val starting_money: Double, val starting_lives: Double)
{
    def get_next_wave: Option[Wave]
}

object Level
{

    private class LevelFromList(override val name: String, override val map: GameMap, override val starting_money: Double, override val starting_lives: Double, val list: List[Wave])
      extends Level(name, map, starting_money, starting_lives)
    {
        override def get_next_wave: Option[Wave] =
        {
            list.headOption
        }
    }

    def from_list(name: String, map: GameMap, starting_money: Double, starting_lives: Double, list: List[Wave]): Level =
    {
        new LevelFromList(name, map, starting_money, starting_lives, list)
    }
}