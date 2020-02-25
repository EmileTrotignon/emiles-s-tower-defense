package GameLogic

import java.awt.{Graphics2D, Rectangle}

import GUI.FTimer

import scala.collection.mutable._

import java.awt.event._


class GameLogic(map: GameMap, starting_money: Double, starting_lives: Double, var next_levels: List[Level])
{
    val board: BoardLogic = new BoardLogic(map)

    val player: PlayerLogic = new PlayerLogic(starting_money, starting_lives)

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

    def spawn_monster(monster: Monster): Unit =
    {
        board.monsters.addOne(monster)
    }

    def spawn_tower(tower: Tower): Unit =
    {
        board.towers.addOne(tower)
    }
}
