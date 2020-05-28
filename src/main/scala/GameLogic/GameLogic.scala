package GameLogic

import GUI.{FSignal, FTimer}
import GameLogic.Towers.Tower

class GameLogic(val level: Level)
{
    level.start

    val map: GameMap = level.map

    val board: BoardLogic = new BoardLogic(map)

    val player: PlayerLogic = new PlayerLogic(level.starting_money, level.starting_lives)

    board.monster_in_base_signal.add_callback(player.monster_in_base)
    board.monster_died_signal.add_callback(player.killed_monster)
    val tick_interval: Int = 1000 / 120

    val timer: GUI.FTimer = new FTimer(tick_interval, _ =>
    {
        board.tick_board()
        if (current_wave != null)
        {
            current_wave.tick_wave(this)
            if(current_wave.is_finished)
            {
                current_wave.end() //B
                if (level.is_finished)
                {
                    if (board.is_deserted) //A
                        you_win_signal.emit()
                }
                else
                    last_subwave_but_not_last_wave_signal.emit()
            }
        }
    })
    timer.start()

    var current_wave: Wave = _

    val you_win_signal = new FSignal[Unit]
    
    val new_wave_signal = new FSignal[Unit]
    val last_subwave_but_not_last_wave_signal = new FSignal[Unit]

    def start_next_wave(): Unit =
    {
        level.get_next_wave match
        {
            case Some(wave) =>
                new_wave_signal.emit()
                //on fait ca :
                wave.start
                current_wave = wave
                /* plutot que ca :
                current_wave = wave //C
                current_wave.start //D
                afin d'eviter l'ordre d'execution C A D B qui poserait probleme*/
            case None => you_win_signal.emit() // n'est pas suppose se produire
          }
    }

    def build_tower(pos_square: Int2, cost: Double, constructor: (Int2, SizeInfo) => Tower): Unit =
    {
        assert(cost <= player.money)
        player.money -= cost
        val tower = constructor(pos_square, new SizeInfo(map))
        board.towers.addOne(tower)
    }
    
    def convert_tile(pos_square: Int2, cost: Double, player_side: Boolean): Unit =
    {
        assert(cost <= player.money)
        player.money -= cost
        board.map.set_tile(pos_square, if (player_side) TowerTile() else SpecialMonsterTile())
    }
    
    def abandon(): Unit = player.lives = 0
}
