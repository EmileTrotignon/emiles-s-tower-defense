package GameLogic

abstract class Tower(override var position : Point) extends BoardObject
{
  val square : Square //position sur la grille (pas sur l'image). La position sur l'imagedoit être déduite à l'affichage
  val damage : Int
  val period : Int
  val reach : Int //portée exprimée en nombre de pixels
  val cost : Int

  def build : Unit => Unit
}
