package GameLogic

import scala.collection.mutable
import scala.math.Ordering
import scala.collection.mutable.PriorityQueue

/*
object closer_square extends Ordering[(Int2, Int)]
{
    override compare(Int2, Int)
}*/


class Routes(map: GameMap)
{

  val size_info = new SizeInfo(map)
  var routes: Map[Int2, (Int2, Int)] = Map[Int2, (Int2, Int)]()

  def square_neighbours: Array[Int2] = Array(Int2(1, 0), Int2(-1, 0), Int2(0, 1), Int2(0, -1))

  def update_routes(): Unit =
  {

    val next_squares: mutable.PriorityQueue[(Int2, Int)] = mutable.PriorityQueue[(Int2, Int)]()(Ordering.by((s: (Int2, Int)) => -s._2))

    map.iterator.foreach(sq =>
      {
        val t = map.get_tile(sq)
        t match
        {
          case BaseTile() =>
            routes -= sq
            next_squares += (sq -> Some(0))
            routes += (sq -> (sq, Some(0)))
          case MonsterTile() =>
            routes -= sq
            routes += (sq -> (sq, None))
          case _ =>
            val destination = map.iterator.filter(
              sq => map.get_tile(sq) == MonsterTile()
            ).minBy(
              sq2 => Double2.squared_dist(size_info.square_center(sq), size_info.square_center(sq2))
            )
            routes += (sq -> (destination, None))
        }
      }
    )

    while (next_squares.nonEmpty)
    {
      val (sq, dist) = next_squares.dequeue()
      square_neighbours.foreach(delta =>
        {
          val neighbour = sq + delta
          val border = map.size
          if
            (
              neighbour.x < border.x && neighbour.x >= 0
                && neighbour.y < border.y && neighbour.y >= 0
                &&
                (
                  map.get_tile(neighbour) match
                  {
                    case BaseTile() => true
                    case MonsterTile() => true
                    case _ => false
                  }
                )
                && (!routes.contains(neighbour)
                  || (routes(neighbour)._2 match
                    case None => true
                    case Some(d) => d > dist))
            )
          {
            routes -= neighbour
            routes += (neighbour -> (sq, Some(dist + 1)))
            next_squares.addOne((neighbour, Some(dist + 1)))
          }
        }
      )
    }
  }

  update_routes()

  def next_target(position: Double2): Int2 =
  {
    val info = new SizeInfo(map)
    routes(info.logic_to_square(position))._1
  }
}
