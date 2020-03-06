package GameLogic

import java.awt.event.ActionEvent
import java.awt.{AWTEvent, Graphics2D, Rectangle}

import GUI.FSignal

import scala.collection.mutable

class BoardLogic(val map: GameMap)
{

    object MonsterInBaseActionEvent extends AWTEvent(BoardLogic.this, 0)
    {

    }

    var monsters: mutable.Set[Monster] = mutable.Set[Monster]()
    var towers: mutable.Set[Tower] = mutable.Set[Tower]()
    var bullets: mutable.Set[Bullet] = mutable.Set[Bullet]()

    val monster_in_base_signal: FSignal[Monster] = new FSignal[Monster]()
    val monster_died_signal: FSignal[Monster] = new FSignal[Monster]()

    def is_deserted: Boolean = //tells whether there is still living monsters
    {
        monsters.isEmpty
    }

    def tower_at_square(square: Int2): Option[Tower] =
    {
        towers.find(t => t.square == square)
    }

    def paint_board(size_info: SizeInfoPixels, g: Graphics2D): Unit =
    {
        map.paint_map(size_info, g)

        monsters.foreach(m => m.paint(size_info, g))
        towers.foreach(t => t.paint(size_info, g))
        bullets.foreach(b => b.paint(size_info, g))
    }

    def tick_board(): Unit =
    {
        monsters.filterInPlace(p =>
        {
            if (p.dead)
            {
                monster_died_signal.emit(p)
            }
            !p.dead && p.position.is_in_bounds(0, 0, map.width().toDouble, map.height().toDouble)
        })
        bullets.filterInPlace(p => p.position.is_in_bounds(0, 0, map.width().toDouble, map.height().toDouble))
        monsters.foreach(m =>
        {
            val collisions = bullets.filter(b => b.is_colliding(m))
            collisions.foreach(b =>
            {
                b.make_damage(m)
            })
            bullets = bullets.diff(collisions)
            val square = Double2.floor(m.position)
            map.get_tile(square) match
            {
                case BaseTile() =>
                    monster_in_base_signal.emit(m)
                case _ => ()
            }
        })

        monsters.foreach(m => m.tick(this))
        towers.foreach(m => m.tick(this))
        bullets.foreach(m => m.tick(this))
    }

    def spawn_bullet(bullet: Bullet): Unit =
    {
        bullets.addOne(bullet)
    }
}

object BoardLogic
{

}