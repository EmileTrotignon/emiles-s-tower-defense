
abstract class Monster extends BoardObject
{
    var max_hp: Float
    var hp: Float
    var speed: Int
    var loot: Int

    def take_damage: Int => Unit
}


class Triangle extends Monster
{

}