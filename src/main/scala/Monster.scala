abstract class Monster extends BoardObject
{
  val max_hp: Float
  var hp: Float
  val speed: Int
  val loot: Int

  def take_damage: Int => Unit

  def spawn : Unit -> Unit : ()//TODO
}

class Wave
{
  val monsters : List[Monster]

  def spawn = spawn_aux monsters
  private def spawn_aux(ennemies : List[Monster]) =
  {
    enemies match
    {
      ennemy :: next =>
      {
        ennemy.spawn
        spawn_aux next
      }
      Nil => ()
    }
  }

  def update = ()//TODO

}

class Triangle extends Monster
{

}
