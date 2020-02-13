package GameLogic

abstract class Tower(override var position : Point) extends BoardObject
{
  val square : Square //position sur la grille (pas sur l'image). La position sur l'image doit être déduite à l'affichage
  val damage : Double
  val period : Int
  val reach : Int //portée exprimée en nombre de pixels
  val cost : Double

  def build(): Unit
}

object Towers
{
  import javax.swing._
  import javax.swing.JPanel

  var available_towers: List[JButton] = Nil //TODO Reste à géger cette liste. Pour la 1ère version, on peut faire que tous les types de tower sont disponibles dès le début


  /* TODO je sais pas comment faire plus court et je vais me faire jetter de la 411 bientôt */
  def show_available(panel_select_tower: JPanel): Unit =
  {
    show_available_aux(panel_select_tower, available_towers)
  }

  def show_available_aux(panel_select_tower: JPanel, towers: List[JButton]): Unit =
  {
    towers match
    {
      case select_tower_button :: next_buttons =>
        panel_select_tower.add(select_tower_button)
        show_available_aux(panel_select_tower, next_buttons)
      case Nil => ()
    }
  }
}
