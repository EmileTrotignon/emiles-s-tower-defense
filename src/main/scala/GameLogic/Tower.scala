package GameLogic

import java.awt.{Color, Graphics2D}

import TowerTypes._

abstract class Tower(val square: Int2 = null, size_info: SizeInfo) extends BoardObject
{
    override var position: Double2 = size_info.square_center(square)
    protected val damage: Double
    protected val period: Int
    protected val _reach: Double

    def reach: Double = _reach

    def build(): Unit =
    {}


}

case class TowerType(name: String, constructor: (Int2, SizeInfo) => Tower, cost: Double)
{

}

object Towers
{
    var tower_constructors: Array[TowerType] = Array(TowerType("Square tower", SquareTower, 5), TowerType("Round tower", RoundTower, 10), TowerType("Laser tower", LaserTower, 10))
}
