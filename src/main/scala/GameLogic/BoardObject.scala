package GameLogic

import java.awt.Graphics2D

abstract class BoardObject
{
  var position: Point2DDouble //position sur l'image (pas sur la grille)

  def paint(g : Graphics2D) : Unit

  def tick(b : BoardLogic) : Unit

}

