package GameLogic

import java.awt.{Graphics2D, Rectangle}

import GUI.FTimer

import scala.collection.mutable

import java.awt.event._


class BoardLogic(val map: GameMap, var next_levels: List[Level])
{
    var monsters: mutable.Set[Monster] = mutable.Set[Monster]()
    var towers: mutable.Set[Tower] = mutable.Set[Tower]()
    var bullets: mutable.Set[Bullet] = mutable.Set[Bullet]()
    var currentLevel: Level = new Level(Nil)
    
    def start_next_level(game_logic: GameLogic): Boolean =
    {
        next_levels match
        {
            case level :: next =>
                next_levels = next
                currentLevel = level
                currentLevel.start(game_logic)
                true
            case Nil => false
        }
    }
    
    def isDeserted(): Boolean = //tells whether there is still living monsters 
    {
        var deserted = true
        monsters.foreach(m => deserted = false)
        deserted
    }
    
    def tick_board(): Unit =
    {
        monsters.filterInPlace(p => p.position.is_in_bounds())
        bullets.filterInPlace(p => p.position.is_in_bounds())
        monsters.foreach(m => m.tick(this))
        towers.foreach(m => m.tick(this))
        bullets.foreach(m => m.tick(this))
        if(isDeserted() && currentLevel.isFinished())
            currentLevel.end()
    }

    def paint_board(g: Graphics2D, square_size: (Int, Int), bounds: Rectangle): Unit =
    {
        map.paint_map(g, square_size, bounds)
        monsters.foreach(m => m.paint(g))
        towers.foreach(m => m.paint(g))
        bullets.foreach(m => m.paint(g))
    }

    def spawn_bullet(bullet: Bullet): Unit =
    {
        bullets.addOne(bullet)
    }
}

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
              alarmCallBack = doNothingCallBack(_)
              alarmCallBack(a)
        }
    }
    
    val alarm: GUI.FTimer = new GUI.FTimer(100000/60, a => alarmCallBack(a))
    
    def doNothingCallBack(a: ActionEvent) = ()
    var alarmCallBack = doNothingCallBack(_) 
    
    def start(game_logic: GameLogic): Unit =
    {
        alarmCallBack = spawn_wave(game_logic)(_)
        alarm.start()
    }
    
    def end(): Unit =
    {
        alarm.stop()
    }

    def isFinished(): Boolean = Nil == next_waves
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
