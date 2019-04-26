import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    private JFrame frame;
    private JPanel panelTop;
    private JLabel labelResult;
    private JLabel labelInput;
    private JPanel panelBottom;
    final String[] buttonLabels = new String[] {"AC",      "C", "/",
                                                "7",  "8", "9", "*",
                                                "4",  "5", "6", "-",
                                                "1",  "2", "3", "+",
                                                "0",       ".", "="};

    private double lTerm;
    private double rTerm;
    private String operation;
    private boolean isInputLTerm;


    Calculator() {
        frame = new JFrame("Katarinin kalkulator");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        labelResult = new JLabel("Rezultat");
        panelTop.add(labelResult);
        labelInput = new JLabel("Kreni unos...");
        panelTop.add(labelInput);
        frame.add(panelTop);

        panelBottom = new JPanel();
        panelBottom.setLayout(new GridBagLayout());
        int offset = 0;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        for(int i = 0; i < buttonLabels.length; i++) {
            int gridx = i % 4;
            gridx += offset; // grix = gridx + offset
            int gridy = Math.floorDiv(i, 4);
            if(gridx > 3) {
                gridx = 0;
                gridy++;
            }
            gridBagConstraints.gridx = gridx;
            gridBagConstraints.gridy = gridy;

            if(buttonLabels[i].equals("AC") || buttonLabels[i].equals("0")) {
                gridBagConstraints.gridwidth = 2;
                offset++;
            } else {
                gridBagConstraints.gridwidth = 1;
            }

            JButton button = new JButton(buttonLabels[i]);
            button.addActionListener(new CalculatorActionListener());
            panelBottom.add(button, gridBagConstraints);
        }
        frame.add(panelBottom);

        frame.setSize(450, 450);
        frame.setVisible(true);

        lTerm = 0.0;
        rTerm = 0.0;
        operation = "";
        isInputLTerm = true;
    }

    class CalculatorActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String source = e.getActionCommand();
            if(source.equals("+") || source.equals("-") ||source.equals("*") ||source.equals("/")) {
                operation = source;
                isInputLTerm = false;
            } else if(source.equals("AC")) {
                lTerm = 0.0;
                rTerm = 0.0;
                operation = "";
                isInputLTerm = true;
            } else {
                if(isInputLTerm) {
                    lTerm *= 10;
                    lTerm += Double.parseDouble(source);
                } else {
                    rTerm *= 10;
                    rTerm += Double.parseDouble(source);
                }
            }

            if(operation.equals("")) {
                if(lTerm == 0.0) {
                    labelInput.setText("Kreni unos...");
                } else {
                    StringBuilder builder = new StringBuilder();
                    builder.append(lTerm);
                    labelInput.setText(builder.toString());
                }
            } else {
                StringBuilder builder = new StringBuilder();
                builder.append(lTerm);
                builder.append(" ");
                builder.append(operation);
                builder.append(" ");
                builder.append(rTerm);
                labelInput.setText(builder.toString());
            }
        }
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
    }
}
