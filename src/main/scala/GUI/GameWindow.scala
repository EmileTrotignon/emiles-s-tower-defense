package GUI

import javax.swing._
import java.awt.{BorderLayout, Canvas, Graphics}

import GameLogic._


class GameWindow extends JFrame("Emiles's Tower Defense")
{
    val kek = Array(1, 2, 3)
    val game_logic = new GameLogic(new GameMap(Array(
        Array(true, true, false, true, true),
        Array(true, true, false, true, true),
        Array(true, true, false, true, true),
        Array(true, true, false, true, true),
        Array(true, true, false, true, true),
        Array(true, true, false, true, true)))
        , 10
        , 10
        , List())
    val build_tower_button = new JButton("Build tower")
    val next_level_button = new JButton("Start next level")
    val canvas = new GameBoardCanvas(game_logic)

    import javax.swing.JPanel
    import java.awt.BorderLayout

    val contentPane = new JPanel(new BorderLayout)
    //contentPane.setBorder(someBorder)
    val panel_page_end = new JPanel()
    contentPane.add(panel_page_end, BorderLayout.PAGE_END)
    contentPane.add(canvas, BorderLayout.CENTER)
    panel_page_end.add(build_tower_button)
    panel_page_end.add(next_level_button);
    //contentPane.add(build_tower_button, BorderLayout.PAGE_END)
    //contentPane.add(next_level_button, BorderLayout.PAGE_END)

    this.setContentPane(contentPane);
    //this.getContentPane.add(build_tower_button, BorderLayout.C)
    //this.add(next_level_button)

}
