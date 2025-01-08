import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Ship extends Character{
    protected static int width;
    public Ship(){

        initShip();
    }


    private void initShip(){
        var ii = new ImageIcon("C:\\Users\\magie\\Downloads\\AreaIntruders (1)\\AreaIntruders\\AreaIntruders\\src\\Ship.png");
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        int shipx = Consts.BOARD_WIDTH/2;
        setX(shipx);
        int shipy = 500;
        setY(shipy);
        int shipLives;
        setShipLives(3);
        int points;
        setPoints(0);
    }
    public void act()
    {
        x += dir;
        if (x >= Consts.BOARD_WIDTH - 2 * 50)
        {
            x = Consts.BOARD_WIDTH - 2 * 50;
        }
        if (x <= 2)
        {
            x = 2;
        }
    }
    public void keyPressed(KeyEvent event){
        int key = event.getKeyCode();
        if (key==KeyEvent.VK_LEFT){
            dir = -2;
        }
        if (key == KeyEvent.VK_RIGHT){
            dir = 2;
        }
    }
    public void keyReleased(KeyEvent event){
        int key = event.getKeyCode();
        if (key==KeyEvent.VK_LEFT){
            dir = 0;
        }
        if (key == KeyEvent.VK_RIGHT){
            dir = 0;
        }
    }
}
