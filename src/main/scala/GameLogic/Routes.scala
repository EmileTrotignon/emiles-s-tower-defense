class Routes(map: GameMap)
{
  var routes = Map()

  def create_routes() =
  {
    map.map.foreach(t =>
      val sq = t.to_square()
        if (t = BaseTile)
        {
          routes += (sq -> (sq, 0))
          update_routes(sq, 0)
        }
    )
  }

  create_routes()

  def square_neighbours = new Array(Int2(1, 0), Int2(-1, 0), Int2(0, 1), Int2(0, -1))

  def update_routes((sq: Int2, dist: Int)) =
  {
    square_neighbours.foreach(delta =>
      val neighbour = sq + delta
        val border = map.size()
        if(neighbour.x > border.x || neighbour.x < 0 && neighbour.y < border.y && neighbour.y < 0 || map.map(neighbour.x, neighbour.y) != MonsterTile || (routes.contains(neighbour) && routes(neighbour)._2 <= dist + 1))
          ()
        else
        {
          routes += (neighbour, dist + 1)
        }
    )
  }

  def nextSquare(square: Int2): Int2 =
  {
    routes(square)
  }
}
