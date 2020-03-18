package GameLogic

import GameLogic.Monsters.Monster

class SubWave(val monsters: Map[Double2 => Monster, Int])
{
    def spawn(game_logic: GameLogic, spawn_tile: Int2): Unit =
    {
        monsters.foreach(e =>
        {
            for (i <- 0 until e._2) game_logic.board.spawn_monster(e._1, spawn_tile)
        })
    }

}
