package GameLogic

import java.awt.Graphics2D

abstract class Tower(val square: Point2DInt = null, map: GameMap) extends BoardObject
{
    override var position: Point2DDouble = Point2DInt.square_center(square, map.width(), map.height())
    val damage: Double
    val period: Int
    val reach: Int //portée exprimée en nombre de pixels
    val cost: Double

    def build(): Unit =
    {}
}


case class SquareTower(square_ : Point2DInt, map: GameMap) extends Tower(square_, map)
{
    val damage: Double = 1
    val period: Int = 1
    val reach: Int = 1 //portée exprimée en nombre de pixels
    val cost: Double = 1

    override def paint(g: Graphics2D): Unit =
    {}

    override def tick(b: BoardLogic): Unit =
    {}
}

class TowerType(val name: String, val constructor: (Point2DInt, GameMap) => Tower)
{
}

object Towers
{
    var tower_constructors: Array[TowerType] = Array(new TowerType("Square tower", SquareTower)) //TODO Reste à géger cette liste. Pour la 1ère version, on peut faire que tous les types de tower sont disponibles dès le début
}
