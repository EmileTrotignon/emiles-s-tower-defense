package GameLogic

class SubWave(monster_ : Array[Double2 => Monster])
{
    val monsters: Array[Double2 => Monster] = monster_

    def spawn(game_logic: GameLogic): Unit =
    {
        monsters.foreach(monster => game_logic.board.spawn_monster(monster, Levels.spawn_tile))
    }

}
