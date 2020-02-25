package GameLogic

import java.awt.Graphics2D

abstract class BoardObject
{
  var position: Double2 //position sur l'image (pas sur la grille)

  def paint(size_info: SizeInfo, g: Graphics2D): Unit

  def tick(b: BoardLogic): Unit

}

