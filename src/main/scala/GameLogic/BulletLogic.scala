package GameLogic

abstract class BulletLogic(damage_ : Float, direction_and_speed_ : Vector) extends BoardObject
{
    val damage: Float = damage_
    val direction_and_speed: Vector = direction_and_speed_

    def tick_position(): Unit =
    {
        position = new Point(position + direction_and_speed)
    }

    def is_colliding(monster : Monster): Boolean =
    {
        Coordinates.dist(this.position, monster.position) <= monster.size
    }
}
