package GameLogic

import GameLogic.Towers._

abstract class TileAction
{

}

case class TowerConstruction(constructor: (Int2, SizeInfo) => Tower) extends TileAction
{

}

case class TileConversion(player_side: Boolean) extends TileAction
{

}

case class PlayerAction(name: String, tile_action: TileAction, cost: Double)
{

}

object PlayerActions
{
    var actions: Array[PlayerAction] = Array(
        PlayerAction("Square tower", TowerConstruction(new SquareTower(_, _)), 5),
        PlayerAction("Spray tower", TowerConstruction(new SprayTower(_, _)), 5),
        PlayerAction("Round tower", TowerConstruction(new RoundTower(_, _)), 10),
        PlayerAction("Laser tower", TowerConstruction(new LaserTower(_, _)), 10),
        PlayerAction("Glue tower", TowerConstruction(new GlueTower(_, _)), 10),
        PlayerAction("Heat seeking tower", TowerConstruction(new HeatSeekingTower(_, _)), 10),
        PlayerAction("Convert tile", TileConversion(true), 12),
        PlayerAction("Sacrifice tile", TileConversion(false), 1))
}
