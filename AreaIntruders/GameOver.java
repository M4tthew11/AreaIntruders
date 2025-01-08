import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameOver extends JFrame{
    protected Dimension dimension;
    public GameOver(String message){
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        setTitle("GameOver");
        setSize(400,200);
        setPreferredSize(new Dimension(400,200));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel.setBackground(Color.BLACK);
        dimension= new Dimension(Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
        JLabel gameoverLabel = new JLabel(message);
        gameoverLabel.setFont(new Font("Arial",Font.BOLD,24));
        gameoverLabel.setBounds(200,100,100,100);
        setLocationRelativeTo(null);
        setResizable(false);
        mainPanel.add(gameoverLabel,BorderLayout.CENTER);
        buttonPanel.add(gameoverLabel);

        //buttonPanel.setLayout(new GridLayout(1,2));


        JButton restartB = new JButton("Restart");
        restartB.addActionListener(e -> {
            try {
                new Game();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
        JButton Menub = new JButton("Menu");
        Menub.addActionListener(e -> {
            try {
                new Menu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        buttonPanel.add(restartB,BorderLayout.WEST);
        buttonPanel.add(Menub,BorderLayout.EAST);
        buttonPanel.setBackground(Color.gray);
        mainPanel.add(buttonPanel /*,BorderLayout.CENTER*/);
        add(mainPanel);
        mainPanel.setVisible(true);
        dispose();
    }
}
