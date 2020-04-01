package apportionmentcalculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        setupMenu();

        frame.setVisible(true);

    }

    public static void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu options = new JMenu("Calculate");

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
                table.jeffersonMethod();
            }
        });

        options.add(stateAmount);
        options.add(hamilton);
        options.add(jefferson);
        menuBar.add(options);
        frame.setJMenuBar(menuBar);
    }

    public static void findStateAmount() {
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
    }

}
