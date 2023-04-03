package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

public class PauseGameUI extends GameUI{


  public PauseGameUI(PVector position, PVector direction, Button[] buttons, GameManager manager, PImage background) {
    super(position, direction, buttons, manager, background);
  }

  @Override
  public void draw(){
    window.image(background, 0,0);
    drawMessage();
    for(Button button : buttons){

      button.draw();
    }


  }

  private void drawMessage(){

    // Set the font size and color
    window.textSize(24);
    window.fill(255, 0, 0);

    // Draw the message
    window.text("Press P to unpause game", window.width / 2 - 150, (window.height / 2) + 50);
  }

  public void keyEvent(KeyEvent event){
    if (event.getKey() == 'p' || event.getKey() == 'P') {
      manager.setGameState(1);
    }
  }
  @Override
  void buttonClicked(String label) {
    window.exit();
  }
}
