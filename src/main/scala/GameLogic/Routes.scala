package GameLogic

class Routes(map: GameMap)
{
  var routes: Map[Int2, (Int2, Int)] = Map[Int2, (Int2, Int)]()
  val size_info = new SizeInfo(map)

  def create_routes(): Unit =
  {
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
          routes += (sq -> (sq, 0))
          update_routes(sq, 0)
      }
    }
  }

  create_routes()

  def square_neighbours: Array[Int2] = Array(Int2(1, 0), Int2(-1, 0), Int2(0, 1), Int2(0, -1))

  def update_routes(sq: Int2, dist: Int): Any =
  {
    square_neighbours.foreach(delta =>
    {
      val neighbour = sq + delta
      val border = map.size
      if (!(neighbour.x > border.x
        || neighbour.x < 0 && neighbour.y < border.y && neighbour.y < 0
        || (map.map(neighbour.x, neighbour.y) match
      {
        case MonsterTile() => false
        case _ => true
      })
        || (routes.contains(neighbour) && routes(neighbour)._2 <= dist + 1))
      )

      {
        routes += (sq -> (neighbour, dist + 1))
      }
    }
    )
  }

  def nextSquare(square: Int2): Int2 =
  {
    routes(square)._1
  }
}
