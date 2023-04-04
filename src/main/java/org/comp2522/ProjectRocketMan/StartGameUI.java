package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.event.KeyEvent;

public class StartGameUI extends GameUI{


  public StartGameUI(Button[] buttons, GameManager manager, PImage background) {
    super(buttons, manager, background);
  }

  @Override
  void buttonClicked(String label) {
    switch (label) {
      case "Start" -> manager.setGameState(1);
      case "Leaderboard" -> manager.setGameState(4);
      case "Quit" -> window.exit();
      default -> {
        ;
      }
    }
  }

  @Override
  void keyEvent(KeyEvent keyEvent) {
    //empty
  }
}
