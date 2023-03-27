package org.comp2522.ProjectRocketMan;

import processing.core.PVector;

public abstract class GameUI extends Sprite{

  private Button[] buttons;

  protected Window window;
  public GameUI(PVector position, PVector direction, Button[] buttons) {
    super(position, direction);
    window = Window.getInstance();
  }

  protected void draw(){
    for(Button button : buttons){
      button.draw();
    }
  }

  protected void checkForClicks(){
    for(Button button : buttons){
      if(button.isClicked()){
        buttonClicked(button.getLabel());
      }
    }
  }

  abstract void buttonClicked(String label);
}
