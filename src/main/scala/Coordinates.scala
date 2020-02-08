abstract class Coordinates{
  val x, y : Int
}

class Point extends Coordinates{
  //les coordonnées sont exprimées en pixels
}

class Square extends Coordinates{
  //les coordonnées correspondent à une case sur la grille
}

class Vector extends Coordinates{
  //les coordonnées représentent une direction
}
