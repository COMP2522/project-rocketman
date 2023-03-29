package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

public class DeadGameUI extends GameUI{
  public DeadGameUI(PVector position, PVector direction, Button[] buttons, GameManager manager, PImage background) {
    super(position, direction, buttons, manager, background);
  }

  @Override
  void buttonClicked(String label) {
    switch (label){
      case "Retry":
        manager.resertToStart();
        manager.setGameState(1);
        break;
      case "Quit":
        window.exit();
        break;
      default:
        ;
    }

  }

  @Override
  void keyEvent(KeyEvent keyEvent) {

  }
}
