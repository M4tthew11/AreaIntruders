import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

class Menu extends Main
    {
        protected Object s;
        JComboBox shipcom;
        String fileName = "C:\\Users\\magie\\Downloads\\AreaIntruders (1)\\AreaIntruders\\AreaIntruders\\src\\punkty.txt";
        String textToAppend;


        Menu() throws IOException {
            JFrame f = new JFrame();
            f.setPreferredSize(new Dimension(668, 668));
            f.setContentPane(new JPanel() {
                final Image image = ImageIO.read(getClass().getResource("AreaIntrudersTitle3.png"));

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 120, 100, 470, 100, this);
                }


            });
            JTextField textField = new JTextField("Wpisz Nick:");
            textField.setBounds(0, 250, 150, 50);


            JButton b = new JButton();
            Image bimage = ImageIO.read(getClass().getResource("B1.png"));
            b.setIcon(new ImageIcon(bimage));
            b.setBounds(170, 260, 280, 60);
            b.setContentAreaFilled(false);
            b.setOpaque(false);
            b.setBorderPainted(false);

            b.addActionListener(e ->
            {


                try {
                    new Game();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                f.dispose();
                s = shipcom.getSelectedItem();
            });

            JButton bOK = new JButton("Zatwierdź");
            bOK.setBounds(0, 300, 100, 20);
            bOK.addActionListener(e -> {
                        textToAppend = textField.getText();

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                            writer.write(textToAppend);
                            writer.newLine();
                        } catch (IOException exception) {
                            System.err.println("Wystąpił błąd podczas dopisywania do pliku: " + exception.getMessage());
                        }
                    });


            JButton b2 = new JButton();
            Image b2image = ImageIO.read(getClass().getResource("B4.png"));
            b2.setIcon(new ImageIcon(b2image));
            b2.setBounds(260, 500, 105, 55);
            b2.setContentAreaFilled(false);
            b2.setOpaque(false);
            b2.setBorderPainted(false);

            b2.addActionListener(e ->
                    f.dispose());

            JButton b3 = new JButton();
            Image b3image = ImageIO.read(getClass().getResource("B3.png"));
            b3.setIcon(new ImageIcon(b3image));
            b3.setBounds(170, 335, 280, 60);
            b3.setContentAreaFilled(false);
            b3.setOpaque(false);
            b3.setBorderPainted(false);

            b3.addActionListener(e ->
            {
                try {
                    new Scoreboard();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                f.dispose();
            });


            JButton b4 = new JButton();
            Image b4image = ImageIO.read(getClass().getResource("B2.png"));
            b4.setIcon(new ImageIcon(b4image));
            b4.setBounds(260, 420, 105, 55);
            b4.setContentAreaFilled(false);
            b4.setOpaque(false);
            b4.setBorderPainted(false);

            b4.addActionListener(e ->
            {
                try {
                    new Help();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                f.dispose();
            });
            Object s1 = new ImageIcon("Ship.png");
            Object s2 = new ImageIcon("Ship2.png");
            Object s3= new ImageIcon("Ship3.png");

            Object[] shipObjects = {s1,s2,s3};
            shipcom = new JComboBox<>(shipObjects);
            shipcom.setBounds(0, Consts.BOARD_HEIGHT/2,75,50);
            shipcom.setOpaque(false);
            s = shipcom.getSelectedItem();
            f.add(shipcom);

            f.add(b);
            f.add(b2);
            f.add(b3);
            f.add(b4);
            f.add(textField);
            f.add(bOK);
            f.getContentPane().setBackground(Color.BLACK);
            f.setTitle("Area Intruders");
            f.setLayout(null);
            f.setVisible(true);
            f.setResizable(false);
            f.setSize(Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }

    }

    class Help extends Main {
        Help() throws IOException {
            JFrame f1 = new JFrame();
            f1.setContentPane(new JPanel() {
                final Image timage = ImageIO.read(getClass().getResource("B2.png"));
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(timage,220,50,150,80,this);
                }
            });


            JButton b3 = new JButton();
            Image b3image = ImageIO.read(getClass().getResource("B4.png"));
            b3.setIcon(new ImageIcon(b3image));
            b3.setBounds(265, 570, 100, 50);
            b3.setBackground(Color.BLACK);
            b3.addActionListener(e ->
            {
                try {
                    new Menu();
                } catch (IOException e1) {
                    System.out.println("Nothing");
                    e1.printStackTrace();
                }
                f1.dispose();
            });


            f1.add(b3);
            f1.getContentPane().setBackground(Color.BLACK);
            f1.setTitle("Area Intruders");
            f1.setLayout(null);
            f1.setVisible(true);
            f1.setResizable(false);
            f1.setSize(Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
            f1.setLocationRelativeTo(null);
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
        class Scoreboard extends Main {
            Scoreboard() throws IOException {
                JFrame f1 = new JFrame();
                f1.setContentPane(new JPanel() {
                    final Image timage = ImageIO.read(getClass().getResource("B3.png"));
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(timage,220,50,200,90,this);
                    }
                });


                JButton b3 = new JButton();
                Image b3image = ImageIO.read(getClass().getResource("B4.png"));
                b3.setIcon(new ImageIcon(b3image));
                b3.setBounds(265, 570, 100, 50);
                b3.setBackground(Color.BLACK);
                b3.addActionListener(e ->
                {
                    try {
                        new Menu();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    f1.dispose();
                });


                f1.add(b3);
                f1.getContentPane().setBackground(Color.BLACK);
                f1.setTitle("Area Intruders");
                f1.setLayout(null);
                f1.setVisible(true);
                f1.setResizable(false);
                f1.setSize(Consts.BOARD_WIDTH, Consts.BOARD_HEIGHT);
                f1.setLocationRelativeTo(null);
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
