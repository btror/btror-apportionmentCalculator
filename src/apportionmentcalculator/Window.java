package apportionmentcalculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author brorie3
 */
public class Window {

    private static JFrame frame;
    private static JPanel panel;
    private static Table table;
//    private static JPanel bottom;

    private static int amountOfStates;
    private static int amountOfSeats;

    public Window() throws IOException {
        frame = new JFrame();
        frame.setTitle("Apportionment Calculator");
        frame.setSize(new Dimension(1000, 750));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridLayout(1, 1));
        panel.setBackground(Color.PINK);
        frame.add(panel);

        table = new Table(6, 7, 30);
        table.setup();

        panel.add(table.getInfo());

        //bottom = new JPanel(new BorderLayout());
        //bottom.setBackground(Color.PINK);
        //panel.add(bottom);
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        InputStream input = classLoader.getResourceAsStream("TICER2.png");
//        BufferedImage image = ImageIO.read(input);
//        BufferedImage resized = resize(image, 500, 500);
//        JLabel l = new JLabel(new ImageIcon(resized));
//        bottom.add(l);
        setupMenu();

        frame.setVisible(true);

    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public static void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu options = new JMenu("options");

        JMenuItem stateAmount = new JMenuItem("Change Amount of States");

        stateAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //method to gather amount of states
                findStateAmount();
                System.out.println("Amount of states: " + amountOfStates);
            }
        });

        JMenuItem hamilton = new JMenuItem("Execute Hamilton's Method");

        hamilton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //method for hamilton
                table.hamiltonMethod();
            }
        });

        JMenuItem jefferson = new JMenuItem("Execute Jefferson's Method");
        jefferson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        options.add(stateAmount);
        options.add(hamilton);
        options.add(jefferson);
        menuBar.add(options);
        frame.setJMenuBar(menuBar);
    }

    public static void findStateAmount() {
        //try {
        String m = JOptionPane.showInputDialog("Enter amount of states");
        amountOfStates = Integer.parseInt(m);
        String m1 = JOptionPane.showInputDialog("Enter amount of seats: ");
        amountOfSeats = Integer.parseInt(m1);
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        table = new Table(6, amountOfStates, amountOfSeats);
        table.setup();
        JPanel p = table.getInfo();
        panel.add(p);
//            bottom = new JPanel(new BorderLayout());
//            bottom.setBackground(Color.PINK);
//            panel.add(bottom);

//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            InputStream input = classLoader.getResourceAsStream("TICER2.png");
//            BufferedImage image = ImageIO.read(input);
//            BufferedImage resized = resize(image, 500, 500);
//            JLabel l = new JLabel(new ImageIcon(resized));
//            bottom.add(l);
//        } catch (NumberFormatException e) {
//
//        } catch (IOException ex) {
//            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}

