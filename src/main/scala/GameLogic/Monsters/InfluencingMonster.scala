package GameLogic.Monsters

import java.awt.{Color, Graphics2D}
import scala.collection.mutable

import GameLogic._
import Graphics.{Drawing, DrawingElement}

abstract class InfluencingMonster(position_ : Double2, val reach: Double, val reach_color: Color) extends ModifierMonster(position_)
{
    override def is_applicable(m: Monster): Boolean =
    {
        this.dead == false &&
        Double2.dist(m.position, position) <= reach
    } 
    
    val reached_area = new DrawingElement(filled_up = false, reach_color, Graphics.Circle(Double2(0, 0), reach))
    
    override def paint(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        layer match
        {
            case Layers.life_bars =>
                reached_area.paint(size_info, g, position, 1)
            case _ => ()
        }
        super.paint(size_info, layer, g)
    }
}