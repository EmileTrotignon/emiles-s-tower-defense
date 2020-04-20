package GameLogic

abstract class Level(val name: String, val map: GameMap, val starting_money: Double, val starting_lives: Double)
{
    def start: Unit
    def get_next_wave: Option[Wave]
}

object Level
{
    private class LevelFromList(override val name: String, override val map: GameMap, override val starting_money: Double, override val starting_lives: Double, val waves: List[Wave])
      extends Level(name, map, starting_money, starting_lives)
    {
        var next_waves: List[GameLogic.Wave] = Nil
        
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