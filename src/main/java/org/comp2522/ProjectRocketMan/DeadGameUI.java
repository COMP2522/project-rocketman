package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

public class DeadGameUI extends GameUI{
  public DeadGameUI(Button[] buttons, GameManager manager, PImage background) {
    super(buttons, manager, background);
  }

  @Override
  void buttonClicked(String label) {
    switch (label) {
      case "Retry" -> {
        manager.resetToStart();
        manager.setGameState(1);
      }
      case "Main Menu" -> manager.setGameState(0);
      default -> {
        ;
      }
    }

  }

  @Override
  void keyEvent(KeyEvent keyEvent) {
    //no key events
  }
}
