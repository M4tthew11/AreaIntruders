import javax.swing.*;

public class Shot extends Character{
    public Shot()
    {

    }

    public Shot(int x, int y)
    {
        var shotImg = "shot.png";
        var imageIcon = new ImageIcon(shotImg);
        setImage(imageIcon.getImage());

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
