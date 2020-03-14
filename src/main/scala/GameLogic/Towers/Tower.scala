package GameLogic.Towers

import java.awt.{Color, Graphics2D}

import GameLogic._
import Graphics.{Drawing, DrawingElement}

abstract class Tower(val square: Int2 = null, size_info: SizeInfo) extends BoardObject
{
    override var position: Double2 = size_info.square_center(square)
    override val size: Double = 1
    protected val damage: Double
    protected val period: Int
    protected val _reach: Double
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.green, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))

    override def paint(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        layer match
        {
            case Layers.towers =>
                super.paint(size_info, layer, g)
            case _ => ()
        }
    }


    def reach: Double = _reach

    def build(): Unit =
    {}


}

case class TowerType(name: String, constructor: (Int2, SizeInfo) => Tower, cost: Double)
{

}

object Tower
{
    var tower_constructors: Array[TowerType] = Array(Towers.TowerType("Square tower", SquareTower, 5), Towers.TowerType("Round tower", RoundTower, 10), Towers.TowerType("Laser tower", LaserTower, 10))
}
