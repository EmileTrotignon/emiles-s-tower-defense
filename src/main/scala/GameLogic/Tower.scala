package GameLogic

import java.awt.Graphics2D

abstract class Tower(override var position: Point) extends BoardObject
{
    val square: Square = null //position sur la grille (pas sur l'image). La position sur l'image doit être déduite à l'affichage
    val damage: Double
    val period: Int
    val reach: Int //portée exprimée en nombre de pixels
    val cost: Double

    def build(): Unit =
    {}
}


case class SquareTower(p: Point) extends Tower(p)
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

class TowerType(val name : String, val constructor : Point => Tower)
{
}

object Towers
{
    var tower_constructors: Array[TowerType] = Array(new TowerType("Square tower", SquareTower)) //TODO Reste à géger cette liste. Pour la 1ère version, on peut faire que tous les types de tower sont disponibles dès le début
}
