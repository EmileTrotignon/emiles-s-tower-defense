import scala.swing

abstract class BoardObject
{
  val position : Point //position sur l'image (pas sur la grille)
  val sprite: Option[ImageIcon]
  def update : Unit => Unit
}



