package org.comp2522.ProjectRocketMan;

import processing.core.PVector;

import javax.swing.text.StyleContext;

public abstract class GameUI extends Sprite{

  protected Button[] buttons;

  protected Window window;
  protected GameManager manager;


  public GameUI(PVector position, PVector direction, Button[] buttons, GameManager manager) {
    super(position, direction);
    window = Window.getInstance();
    this.manager = manager;
    this.buttons = buttons;
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
