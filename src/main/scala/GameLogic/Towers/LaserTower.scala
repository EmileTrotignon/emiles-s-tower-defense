package GameLogic.Towers

import java.awt.{Color, Graphics2D}

import GameLogic._
import _root_.GameLogic.Monsters.Monster
import _root_.GameLogic.{Double2, Int2, SizeInfo}
import Graphics.{Drawing, DrawingElement}

class LaserTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info)
{
    val damage: Double = 0.03
    val period: Int = 0
    val _reach: Double = 4
    val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.green, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle(Double2(0, 0), 0.36)),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle(Double2(0, 0), 0.12)),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle(Double2(0, 0), 0.04))))

    var shooting_monster: Option[Monster] = None

    private var tick: Int = 0


    override def paint(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        layer match
        {
            case Layers.towers =>
                super.paint(size_info, layer, g)
            case Layers.bullets =>
                g.setColor(Color.yellow)
                shooting_monster match
                {
                    case Some(monster) => Graphics.draw_line(size_info, position, monster.position, g)
                    case None => ()
                }
            case _ => ()
        }
    }

    override def tick(b: BoardLogic): Unit =
    {
        if (b.monsters.nonEmpty)
        {
            update_laser(b)
        }


        shooting_monster match
        {
            case Some(monster) => monster.take_damage(damage)
            case None => ()
        }
        tick += 1
    }

    def update_laser(b: BoardLogic): Unit =
    {
        val monster = b.monsters.minBy(m => Double2.squared_dist(_position, m.position))
        if (Double2.dist(monster.position, position) <= _reach)
        {
            shooting_monster = Some(monster)
        } else
        {
            shooting_monster = None
        }
        shooting_monster match
        {
            case Some(monster) => if (monster.dead) shooting_monster = None
            case None => ()
        }
    }
}
