abstract class Tower(p : Coordinates) extends BoardObject(p)
{
  val square : Square //position sur la grille (pas sur l'image). La position sur l'imagedoit être déduite à l'affichage
  val damage : Int
  val period : Int
  val reach : Int //portée exprimée en nombre de pixels
  val cost : Int

  def build : Unit => Unit
}

abstract class Bullet extends BoardObject
{
  var position : Point
  val direction : Vector
  val damage : Iloat
  val speed : Int

}
