abstract class Tower extends BoardObject
{
    val damage: Int
    val period: Int
    val reach: Float
    val cost: Int
}

abstract class Bullet extends BoardObject
{
    var damage: Float
}
