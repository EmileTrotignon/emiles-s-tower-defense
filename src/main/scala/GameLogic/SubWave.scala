package GameLogic

import GameLogic.Monsters.Monster

class SubWave(val monsters: Map[Double2 => Monster, Int], val spawn_tile: Int2)
{
    def this(sw: (Map[Double2 => Monster, Int], Int2)) = this(sw._1, sw._2)

    def spawn(game_logic: GameLogic): Unit =
    {
        monsters.foreach(e =>
        {
            for (i <- 0 until e._2) game_logic.board.spawn_monster(e._1, spawn_tile)
        })
    }

}
