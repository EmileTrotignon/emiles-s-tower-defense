import scala.swing

abstract class BoardObject
{
  //position
  var x : Float
  var y : Float
  var sprite : Option[ImageIcon]
}

abstract class Tower extends BoardObject
{
  val damage : Int
  val period : Int
  val reach : Float
  val cost : Int
}

abstract class Monster extends BoardObject
{
  var max_hp : Float
  var hp : Float
  var speed : Int
  var loot : Int
  def take_damage : Int => Unit
}

abstract class Bullet extends BoardObject
{
  var damage :
}

class Triangle extends Monster
{

}