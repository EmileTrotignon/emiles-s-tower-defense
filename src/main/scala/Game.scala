class Board
{
  def play_step = ()//TODO update tout le monde
}

class Level(val groups : List[Group])
{
  var next_Waves : List[Group] = waves

  def start =
  {
    //intro éventuelle TODO
  }

  def update = spawn_wave

  def spawn_wave =
  {
    next_waves match
    {
      case wave :: next =>
        {
          next_waves = next
          wave.spawn
          true
        }
      case Nil => false
    }
  }

  def isFinished = (Nil == next_groups)
}

class Player
{
  var money : Int = 100

  def build_tower : Unit => Bool =
  {
    get_tower match
    {
      case None => false //le joueur a cliqué sur "battaille !"
      case Some (tower : Tower) => tower.build()
    }
  }

  //lorsque get_tower renvoie une tower, il doit avoir renseigné son square
  def get_tower : Option[Tower] = None //TODO
}

object Game
{
  val levels : Array[Level] //TODO

  val board : Board
  val player : Player

  def play_turn(level : Level) : Bool =
  {
    //tour du joueur
    while(player.build_tower);

    //tour des monstres
    level.start

    /*
     gestion :
     - du temps
     - de level.update
     - de board.play_step
     - de la condition d'arrêt du turn
     - de la valeur de retour indiquant la victoire du joueur
     */
    false
  }

  def play =
  {
    //initialisations

    /*TODO
     for(level in levels et tant que play_turn);
     */
  }
}
