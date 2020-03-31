package apportionmentcalculator;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author brorie3
 */
public class Table {

    private static JPanel table;
    private static int rows;
    private static int cols;
    private static int seats;
    private static ArrayList<JTextField> state_pops = new ArrayList<>();
    private static ArrayList<Double> quotas = new ArrayList<>();
    private static ArrayList<JLabel> state_quota_values = new ArrayList<>();
    private static ArrayList<Integer> initial_values = new ArrayList<>();
    private static ArrayList<JLabel> initial_v = new ArrayList<>();
    private static ArrayList<Integer> final_values = new ArrayList<>();
    private static ArrayList<JLabel> final_v = new ArrayList<>();

    public Table() {
        table = new JPanel();
    }

    public Table(int rows, int cols, int seats) {
        table = new JPanel(new GridLayout(rows, cols));
        Table.rows = rows;
        Table.cols = cols;
        Table.seats = seats;
    }

    public JPanel getInfo() {
        return table;
    }

    public void setup() {

        state_pops.clear();
        quotas.clear();
        state_quota_values.clear();
        initial_values.clear();
        initial_v.clear();
        final_values.clear();
        final_v.clear();

        //JPanel p = new JPanel(new GridLayout(rows, cols));
        table.setBackground(Color.CYAN);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols + 1; col++) {
                final JLabel label = new JLabel("", SwingConstants.CENTER);
                if (row != 0) {
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                } else if (row == 0 && col == 0) {
                    label.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.BLACK));
                    label.setText("Seats");
                } else if (row == 0 && col == 1) {
                    label.setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.BLACK));
                    label.setText("" + seats);
                }

                if (row == 1 && col == 0) {
                    label.setText("State");
                }
                if (row == 2 && col == 0) {
                    label.setText("Population");
                }
                if (row == 3 && col == 0) {
                    label.setText("Quota");
                }
                if (row == 4 && col == 0) {
                    label.setText("Initial");
                }
                if (row == 5 && col == 0) {
                    label.setText("Final");
                }

                if (row == 1 && col != 0) {
                    label.setText("S" + col);
                }

                if (row == 3 && col != 0) {
                    state_quota_values.add(label);
                }

                if (row == 4 && col != 0) {
                    initial_v.add(label);
                }

                if (row == 5 && col != 0) {
                    final_v.add(label);
                }

                if (row > 0 && col == 0) {
                    label.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.BLACK));
                }

                if (row == 0) {

                    if (col == 0) {
                        label.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.BLACK));
                    }

                    if (col > 0) {
                        if (col == cols) {
                            label.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 2, Color.BLACK));
                        } else {
                            label.setBorder(BorderFactory.createMatteBorder(2, 0, 1, 0, Color.BLACK));
                        }
                        if (col == 1) {
                            label.setBorder(BorderFactory.createMatteBorder(2, 1, 1, 0, Color.BLACK));
                        }
                    }
                }

                if (row == 1 && col > 1) {
                    if (col == cols) {
                        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.BLACK));
                    } else {
                        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                    }
                }
                if (row == 3 && col == cols) {
                    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.BLACK));
                }
                if (row == 4 && col == cols) {
                    label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.BLACK));
                }
                if (row == 5) {
                    if (col == 0) {
                        label.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.BLACK));
                    } else {
                        label.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.BLACK));
                    }
                    if (col == cols) {
                        label.setBorder(BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK));
                    }
                }

                if (row == 2 & col != 0) {
                    JTextField tf = new JTextField();
                    state_pops.add(tf);
                    tf.setHorizontalAlignment(JTextField.CENTER);
                    tf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    if (col == cols) {
                        tf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.BLACK));
                    }
                    table.add(tf);
                } else {
                    table.add(label);
                }
            }
        }
    }

    public void hamiltonMethod() {
        System.out.println("TEST");
        ArrayList<Double> nums = new ArrayList<>();
        nums.clear();
        quotas.clear();
        try {

            int total = 0;
            for (int i = 0; i < state_pops.size(); i++) {
                total += Integer.parseInt(state_pops.get(i).getText());
            }
            int divisor = total / seats;
            for (int i = 0; i < state_pops.size(); i++) {
                double stateValue = Double.valueOf(state_pops.get(i).getText()) / divisor;
                quotas.add(stateValue);
                state_quota_values.get(i).setText(Double.toString(stateValue));
            }

            for (int i = 0; i < quotas.size(); i++) {
                double v = quotas.get(i) - Math.floor(quotas.get(i));
                nums.add(v);
                System.out.println("dvalue: " + v);
            }
            double max = Collections.max(nums);
            int index = 0;
            for (int i = 0; i < nums.size(); i++) {
                if (max == nums.get(i)) {
                    index = i;
                    break;
                }
            }
            for (int i = 0; i < nums.size(); i++) {
                if (i == index) {
                    final_values.add((int) Math.floor(quotas.get(i) + 1));
                } else {
                    final_values.add((int) Math.floor(quotas.get(i)));
                }
                initial_values.add((int) Math.floor(quotas.get(i)));
            }
            for (int i = 0; i < initial_values.size(); i++) {
                initial_v.get(i).setText(Integer.toString(initial_values.get(i)));
            }

            for (int i = 0; i < final_values.size(); i++) {
                final_v.get(i).setText(Integer.toString(final_values.get(i)));
            }

        } catch (NumberFormatException e) {

        }

    }
    
    private static void prioritizeH(ArrayList<Double> decimals, ArrayList<Integer> list, int sum) {
        if (sum != seats) {
            //get the highest decimal
            double max = Collections.max(decimals);
            //locate the index of the highest decimal
            int index = 0;
            for (int i = 0; i < decimals.size(); i++) {
                if (max == decimals.get(i)) {
                    index = i;
                    break;
                }
            }
            //increase the amount of seats of the list at the index of the highest decimal
            list.set(index, list.get(index) + 1);
            //lower the index of the decimal location in the decimal array (so we can resuse this method)
            decimals.set(index, 0.0);
            //check for more inequalities
            sum += 1;
            prioritizeH(decimals, list, sum);
        }
    }

}
