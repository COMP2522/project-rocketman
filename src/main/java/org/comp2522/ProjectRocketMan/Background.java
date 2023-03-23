import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Background {
  private PImage image;
  private float x;
  private float y;
  private float speed;
  private ArrayList<Coin> coins;
  private ArrayList<Zapper> zappers;
  private PApplet parent;

  public Background(PApplet parent, PImage image, float speed) {
    this.parent = parent;
    this.image = image;
    this.speed = speed;
    this.x = 0;
    this.y = 0;
    this.coins = new ArrayList<>();
    this.zappers = new ArrayList<>();
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public void setCoinNum(int numCoins) {
    // TODO: Implement logic to generate `numCoins` coins in random positions on the screen
  }

  public void setZapperNum(int numZappers) {
    // TODO: Implement logic to generate `numZappers` zappers in random positions on the screen
  }

  public void update() {
    // Move the background
    x -= speed;
    if (x <= -image.width) {
      x += image.width;
    }

    // Update the positions of the coins and zappers
    for (Coin coin : coins) {
      coin.update(speed);
    }
    for (Zapper zapper : zappers) {
      zapper.update(speed);
    }
  }

  public ArrayList<Float> getCoinPositions() {
    ArrayList<Float> positions = new ArrayList<>();
    for (Coin coin : coins) {
      positions.add(coin.getPosition());
    }
    return positions;
  }

  public ArrayList<Float> getZapperPositions() {
    ArrayList<Float> positions = new ArrayList<>();
    for (Zapper zapper : zappers) {
      positions.add(zapper.getPosition());
    }
    return positions;
  }

  public void draw() {
    // Draw the background image
    parent.image(image, x, y);
    if (x < 0) {
      parent.image(image, x + image.width, y);
    }

    // Draw the coins and zappers
    for (Coin coin : coins) {
      coin.draw(parent);
    }
    for (Zapper zapper : zappers) {
      zapper.draw(parent);
    }
  }
}
