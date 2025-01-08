import javax.swing.*;
import java.io.FileNotFoundException;

public class Game extends JFrame {
    private JFrame gameFrame;
    private Board board;
    private GameOver gameOver;
    public Game() throws FileNotFoundException {
        gameFrame = new JFrame();
        board = new Board(this,this);
        gameOver = new GameOver(board.getMessage());
        gameFrame.add(board);
        gameFrame.setTitle("Area Intruders");
        gameFrame.setSize(Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
    public void showGameOver(){
        gameOver.show();
        gameFrame.dispose();
    }
    }
