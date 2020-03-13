package GameLogic

import java.lang.reflect.Constructor

import GUI.FTimer

class GameLogic(map: GameMap, starting_money: Double, starting_lives: Double, var next_levels: List[Level])
{
    val board: BoardLogic = new BoardLogic(map)

    val player: PlayerLogic = new PlayerLogic(starting_money, starting_lives)

    board.monster_in_base_signal.add_callback(player.monster_in_base)
    board.monster_died_signal.add_callback(player.killed_monster)
    val tick_interval: Int = 1000 / 60

    val timer: GUI.FTimer = new FTimer(tick_interval, _ =>
    {
        board.tick_board()
        if (current_level != null)
        {
            current_level.tick_level(this)
            if (board.is_deserted && current_level.is_finished)
                current_level.end()
        }
    })
    timer.start()

    var current_level: Level = _

    var is_finished: Boolean = false

    def start_next_level(): Unit =
    {
        is_finished =
          next_levels match
          {
              case level :: next =>
                  next_levels = next
                  current_level = level
                  current_level.start(this)
                  false
              case Nil => true
          }
    }


    def build_tower(pos_square: Int2, cost: Double, constructor: (Int2, SizeInfo) => Tower): Unit =
    {
        assert(cost <= player.money)
        player.money -= cost
        val tower = constructor(pos_square, new SizeInfo(map))
        board.towers.addOne(tower)
    }

}
