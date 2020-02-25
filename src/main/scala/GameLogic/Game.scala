package GameLogic

import java.awt.{Graphics2D, Rectangle}

import GUI.FTimer

import scala.collection.mutable

import java.awt.event._




class Level(val waves: List[Wave])
{
    var next_waves: List[Wave] = waves

    def spawn_wave(game_logic: GameLogic)(a: ActionEvent): Unit =
    {
        next_waves match
        {
            case wave :: next =>
                next_waves = next
                wave.spawn(game_logic)
            case Nil =>
                alarm_callback = do_nothing_callback
                alarm_callback(a)
        }
    }

    val alarm: GUI.FTimer = new GUI.FTimer(100000 / 60, a => alarm_callback(a))

    def do_nothing_callback(a: ActionEvent): Unit = ()

    var alarm_callback: ActionEvent => Unit = do_nothing_callback

    def start(game_logic: GameLogic): Unit =
    {
        alarm_callback = spawn_wave(game_logic)(_)
        alarm.start()
    }

    def end(): Unit =
    {
        alarm.stop()
    }

    def is_finished: Boolean = Nil == next_waves
}

class PlayerLogic(var money: Double, var lives: Double)
{
}

class GameLogic(map: GameMap, starting_money: Double, starting_lives: Double, next_levels: List[Level])
{
    val board: BoardLogic = new BoardLogic(map, next_levels)

    val player: PlayerLogic = new PlayerLogic(starting_money, starting_lives)

    val tick_interval = 10

    val timer: GUI.FTimer = new FTimer(tick_interval, x => board.tick_board())
    timer.start()

    var isFinished: Boolean = false

    def start_next_level(): Unit =
    {
        if(!(board.start_next_level(this)))
            isFinished = true
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
