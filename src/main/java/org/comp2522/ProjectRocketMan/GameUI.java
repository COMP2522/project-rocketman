package org.comp2522.ProjectRocketMan;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import javax.swing.text.StyleContext;

public abstract class GameUI extends Sprite{

  protected Button[] buttons;

  protected Window window;
  protected GameManager manager;
  protected PImage background;


  public GameUI(PVector position, PVector direction, Button[] buttons, GameManager manager, PImage background) {
    super(position, direction);
    window = Window.getInstance();
    this.manager = manager;
    this.buttons = buttons;
    this.background = background;
  }

  protected void draw(){
    window.image(background, 0,0);
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

  abstract void keyEvent(KeyEvent keyEvent);
}
