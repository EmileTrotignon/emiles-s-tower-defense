package GameLogic

abstract class Monster extends BoardObject
{
  val max_hp: Double
  var hp: Double
  val speed: Int
  val loot: Int
  val size : Double

  def take_damage(damage : Float) : Unit =
  {
      hp -= damage
  }

  def spawn() : Unit = ()//TODO
}

class Wave(monster_ :Array[Monster])
{
  val monsters : Array[Monster] = monster_

  def spawn(): Unit =
  {
      monsters.foreach((monster : Monster) => monster.spawn())
  }

}
/*
class Triangle(position_: Point) extends Monster
{
    override val max_hp: Double = 10
    override var hp: Double = max_hp
    override val speed: Int = 1
    override val loot: Int = 1
    override val size: Double = 3
    override var position: Point = position_
}
*/