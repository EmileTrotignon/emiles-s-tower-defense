package GameLogic

class SizeInfo(map: GameMap, val graphics_bounds: java.awt.Rectangle)
{
    val map_size: (Int, Int) = map.size()
}
