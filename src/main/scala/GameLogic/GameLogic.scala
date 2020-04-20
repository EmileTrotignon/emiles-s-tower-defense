package GameLogic

import GUI.FTimer
import GUI.FSignal
import GameLogic.Towers.Tower

class GameLogic(level: Level)
{
    level.start

    val map: GameMap = level.map

    val board: BoardLogic = new BoardLogic(map)

    val player: PlayerLogic = new PlayerLogic(level.starting_money, level.starting_lives)

    board.monster_in_base_signal.add_callback(player.monster_in_base)
    board.monster_died_signal.add_callback(player.killed_monster)
    val tick_interval: Int = 1000 / 60

    val timer: GUI.FTimer = new FTimer(tick_interval, _ =>
    {
        board.tick_board()
        if (current_wave != null)
        {
            current_wave.tick_wave(this)
            if (board.is_deserted && current_wave.is_finished) //A
                current_wave.end() //B
        }
    })
    timer.start()

    var current_wave: Wave = _

    val you_win_signal = new FSignal[Unit]

    def start_next_wave(): Unit =
    {
        level.get_next_wave match
        {
            case Some(wave) =>
                //on fait ca :
                wave.start
                current_wave = wave
                /* plutot que ca :
                current_wave = wave //C
                current_wave.start //D
                afin d'eviter l'ordre d'execution C A D B qui poserait probleme*/
            case None => you_win_signal.emit()
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
