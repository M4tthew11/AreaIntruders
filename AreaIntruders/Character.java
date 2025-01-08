import java.awt.*;

public class Character {
    private  boolean vis;
    private  boolean death;
    private  Image image;
    private int shipLives;
    private int points;
     int x;
     int y;
     int dir;
    public Character(){
        vis = true;
    }

    public boolean isVisible() {
        return vis;
    }

    public  void Death() {
        vis = false;
    }

    public  void setImage(Image image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public  void setDeath(boolean death) {
        this.death = death;
    }

    public  boolean isDeath() {
        return death;
    }

    public void setY(int y) {
        this.y = y;
    }

    public  int getY() {
        return y;
    }

    public  Image getImage() {
        return image;
    }
    public int getLives(){
        return shipLives;
    }

    public void setShipLives(int shipLives) {
        this.shipLives = shipLives;
    }
    public int getpoints(){
        return points;
    }
    public void setPoints(int points){
        this.points = points;
    }
}
