package GameLogic

abstract class BulletLogic(val damage : Double, val direction_and_speed : Point2DDouble) extends BoardObject
{
    override def tick(b : BoardLogic): Unit =
    {
        position = new Point2DDouble(position + direction_and_speed)
    }

    def is_colliding(monster : Monster): Boolean =
    {
        Point2DDouble.dist(this.position, monster.position) <= monster.size
    }
}
