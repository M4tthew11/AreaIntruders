import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board extends JPanel {
    boolean ingame = true;
    protected Dimension dimension;
    private List<Enemies> enemiesList;
    int direction = -1;
    private int deaths = 0;
    String message;
    private Ship ship1;
    private Shot shot1;
    private Timer timer;
    private Timer timer2;
    private final JFrame gameFrame;
    private final Game game1;
    private int time;
    PrintWriter punkty;

    public Board(JFrame gameFrame,Game game) throws FileNotFoundException {
        this.gameFrame = gameFrame;
        this.game1 = game;
        initBoard();
        gameInit();
        punkty = new PrintWriter("C:\\Users\\magie\\Downloads\\AreaIntruders (1)\\AreaIntruders\\AreaIntruders\\src\\punkty.txt");

    }


    public void initBoard() {
        addKeyListener(new KEYAdapter());
        setFocusable(true);
        dimension = new Dimension(Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
        setBackground(Color.BLACK);
        timer = new Timer(Consts.DELAY,new GameCycle());
        timer.start();
        timer2 =new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                repaint();
            }
        });
        timer2.start();
        gameInit();

    }

    private void gameInit() {
        enemiesList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                var enemies = new Enemies(200 + 50 * j, 10 + 50 * i);
                enemiesList.add(enemies);
            }
        }
        ship1 = new Ship();
        shot1 = new Shot();

    }

    private void drawEnemies(Graphics g) {

        for (Enemies enemies : enemiesList) {

            if (enemies.isVisible()) {

                g.fillRect( enemies.getX(), enemies.getY(), Consts.ALIEN_WIDTH, Consts.ALIEN_HEIGHT);
            }

            if (enemies.isDeath()) {

                enemies.Death();
            }
        }
    }

    private void drawShip(Graphics g) {

        if (ship1.isVisible()) {

            g.drawImage(ship1.getImage(), ship1.getX(),ship1.getY(),50,50,this);
            //g.fillRect(ship1.getX(), ship1.getY(),50,50);
        }

        if (ship1.isDeath()) {

            ship1.Death();
            ingame = false;
        }
    }
    private void drawTimer(Graphics g){
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial",Font.PLAIN,20));
        g.drawString(String.valueOf(time),500,550);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            gameDraw(g);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void gameDraw(Graphics g) throws InterruptedException {

        g.setColor(Color.black);
        g.fillRect(0, 0, Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
        g.setColor(Color.green);

        if (ingame) {

            g.drawLine(0, Consts.GROUND, Consts.BOARD_WIDTH, Consts.GROUND);
            g.setColor(Color.RED);
            drawEnemies(g);
            drawShip(g);
            g.setColor(Color.BLUE);
            drawShot(g);
            g.setColor(Color.RED);
            drawBombs(g);
            drawLives(g);
            drawPoints(g);
            drawTimer(g);

        } else {
            if (timer.isRunning()){
                timer.stop();
                timer2.stop();
            }
            game1.showGameOver();


        }
    }

    private void drawShot(Graphics g) {
        if (shot1.isVisible()) {

            g.fillRect( shot1.getX(), shot1.getY(), 2,10);
        }
    }
    private void drawPoints(Graphics g){
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial",Font.PLAIN,20));
        g.drawString("Points: "+ship1.getpoints(),500,30);
    }
    private void drawLives(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,20));
        g.drawString("Lives: " + ship1.getLives(),10,30);
    }
    private void drawBombs(Graphics g) {

        for (Enemies enemies : enemiesList) {

            Enemies.Bomb b = enemies.getBomb();

            if (!b.isDestroyed()) {

                g.fillRect( b.getX(), b.getY(), 2,10);
            }
        }
    }
    protected String getMessage(){
        return message;
    }
    protected void setMessage(String message){
        this.message = message;
    }

    private void update() throws FileNotFoundException {

        if (deaths == Consts.NUM_ALIENS) {

            ingame = false;
            timer.stop();
            timer2.stop();
            setMessage("Game Won!");
                punkty.println(ship1.getpoints());
                punkty.close();
        }
        ship1.act();
        if (shot1.isVisible()) {

            int shotX = shot1.getX();
            int shotY = shot1.getY();

            for (Enemies enemies : enemiesList) {

                int EnemiesX = enemies.getX();
                int EnemiesY = enemies.getY();

                if (enemies.isVisible() && shot1.isVisible()) {
                    if (shotX >= (EnemiesX) && shotX <= (EnemiesX + Consts.ALIEN_WIDTH)
                            && shotY >= (EnemiesY) && shotY <= (EnemiesY + Consts.ALIEN_HEIGHT)) {
                        var imageIcon = new ImageIcon("shot.png");
                        enemies.setImage(imageIcon.getImage());
                        enemies.setDeath(true);
                        deaths++;
                        ship1.setPoints(ship1.getpoints()+100);
                        shot1.Death();

                    }
                }
            }

            int y = shot1.getY();
            y -= 4;

            if (y < 0) {
                shot1.Death();
            } else {
                shot1.setY(y);
            }
        }

        for (Enemies enemies : enemiesList) {

            int x = enemies.getX();

            if (x >= Consts.BOARD_WIDTH - Consts.BORDER_RIGHT && direction != -1) {

                direction = -1;

                for (Enemies a2 : enemiesList) {

                    a2.setY(a2.getY() + 15);
                }
            }

            if (x <= Consts.BORDER_LEFT && direction != 1) {

                direction = 1;

                for (Enemies a : enemiesList) {

                    a.setY(a.getY() + 15);
                }
            }
        }

        for (Enemies enemies : enemiesList) {

            if (enemies.isVisible()) {

                int y = enemies.getY();

                if (y > Consts.GROUND - Consts.ALIEN_HEIGHT) {
                    ingame = false;
                    setMessage("Gameover!");
                        punkty.println(ship1.getpoints());
                        punkty.close();

                }

                enemies.act(direction);
            }
        }
        var generator = new Random();

        for (Enemies enemies : enemiesList) {

            int shot = generator.nextInt(350);
            Enemies.Bomb bomb =enemies.getBomb();

            if (shot == Consts.CHANCE && enemies.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(enemies.getX());
                bomb.setY(enemies.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = ship1.getX();
            int playerY = ship1.getY();

            if (ship1.isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + 50)
                        && bombY >= (playerY)
                        && bombY <= (playerY + 25)) {

                    ship1.setShipLives(ship1.getLives()-1);
                    bomb.setDestroyed(true);
                }
                if (bombX >= (playerX)
                        && bombX <= (playerX + 50)
                        && bombY >= (playerY)
                        && bombY <= (playerY + 25)
                        && ship1.getLives() == 0){

                    ship1.setDeath(true);
                    bomb.setDestroyed(true);
                    punkty.println(ship1.getpoints());
                    punkty.close();
                }

            }

            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= Consts.GROUND - 10) {

                    bomb.setDestroyed(true);
                }
            }
        }
    }

    private void doGameCycle() throws FileNotFoundException {

        update();
        repaint();
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            try {
                doGameCycle();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private class KEYAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            ship1.keyPressed(event);

            int x = ship1.getX();
            int y = ship1.getY();

            int key = event.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (ingame) {

                    if (!shot1.isVisible()) {

                        shot1 = new Shot(x, y);
                    }
                }
            }
        }

        @Override
        public void keyReleased (KeyEvent event){
            ship1.keyReleased(event);
        }
    }
}