package GameLogic

import scala.maths.Ordering
import scala.collection.mutable.PriorityQueue

object closer_square extends Ordering[Int2, Int]
{
  override compare(Int2, Int)
}

class Routes(map: GameMap)
{
  var routes: Map[Int2, Int2] = Map[Int2, Int2]()
  def square_neighbours: Array[Int2] = Array(Int2(1, 0), Int2(-1, 0), Int2(0, 1), Int2(0, -1))

  def create_routes(): Unit =
  {
    val next_squares: PriorityQueue[Int2, Int] = new PriorityQueue[Int2, Int](Ordering.by((s: Int2, Int) => -s._2))
    for
      {i <- 0 until map.size.x
       j <- 0 until map.size.y
      }
    {
      val t = map.get_tile(i, j)
      val sq = Int2(i, j)
      t match
      {
        case BaseTile() =>
          {
            next_squares += (sq, 0)
            routes += (sq -> sq)
          }
      }
    }

    while(!next_squares.isEmpty)
    {
      (sq, dist) = next_squares.dequeue()
      square_neighbours.foreach(delta =>
        {
          val neighbour = sq + delta
          val border = map.size
          if (neighbour.x < border.x && neighbour.x >= 0
            && neighbour.y < border.y && neighbour.y >= 0
            && (map.get_tile(neighbour) match
            {
              case BaseTile() => true
              case MonsterTile() => true
              case _ => false
            })
            && (!routes.contains(neighbour) || routes(neighbour)._2 > dist)
          )
          {
            routes -= neighbour
            routes += (neighbour -> sq)
            next_squares += (neighbour, dist + 1)
          }
        }
      )
    }
  }

  create_routes()

  def next_target(position: Double2): Int2 =
  {
    info = new SizeInfo(map)
    info.square_center(routes(info.logic_to_square(position)))
  }
}
