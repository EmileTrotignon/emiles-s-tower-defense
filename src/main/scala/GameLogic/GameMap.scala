package GameLogic

class GameMap(val map : Array[Array[Boolean]])
{
    def size(): (Int, Int) =
    {
        (map.length, map(0).length)
    }

    def is_buildable(i : Int, j : Int): Boolean =
    {
        map(i)(j)
    }

}
