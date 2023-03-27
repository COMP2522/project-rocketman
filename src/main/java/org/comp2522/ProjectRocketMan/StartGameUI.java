package org.comp2522.ProjectRocketMan;

import processing.core.PVector;

public class StartGameUI extends GameUI{


  public StartGameUI(PVector position, PVector direction, Button[] buttons) {
    super(position, direction, buttons);
  }

  @Override
  void buttonClicked(String label) {
    switch (label){
      case "Start":

        break;
      case "Quit":
        window.exit();
        break;
      default:;
    }
  }
}
