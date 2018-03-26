import calculator.Logic;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JPanel mainPanel;
    private JPanel resultPanel;
    private JLabel label;
    private JTextArea text;
    private JLabel textResult;

    private String TITLE = "Calculator";
    private int SIZE_BUTTON = 70;
    private int WIDTH_FIELD = SIZE_BUTTON * 6 - SIZE_BUTTON / 5;
    Font font = new Font("Times New Roman", 1, SIZE_BUTTON / 2);

    public static void main(String[] args) {
        new Calculator();
    }

    public Calculator() {
        new Logic();
        initResultField();
        initMainField();
        initLabel();
        initFrame();

    }

    private void initMainField() {
        int x, y;
        String[] operation = {"+", "-", "x", "/", "C"};
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JButton[] buttonsNumber = new JButton[10];
        for (int i = 9; i >= 0; i--) {
            x = 2 - ((9 - i) % 3);
            y = (9 - i) / 3;

            buttonsNumber[i] = new JButton();
            buttonsNumber[i].setFont(font);
            buttonsNumber[i].setMargin(new Insets(0, 0, 0, 0));
            buttonsNumber[i].setText(String.valueOf(i));
            buttonsNumber[i].setBounds(x * (SIZE_BUTTON + SIZE_BUTTON / 5) + SIZE_BUTTON / 5, y * (SIZE_BUTTON + SIZE_BUTTON / 5) + SIZE_BUTTON / 5, SIZE_BUTTON, SIZE_BUTTON);
            buttonsNumber[i].addActionListener(new DigitActionListener());
            if (i == 0) {
                x = 0;
                buttonsNumber[i].setBounds(x * (SIZE_BUTTON + SIZE_BUTTON / 5) + SIZE_BUTTON / 5, y * (SIZE_BUTTON + SIZE_BUTTON / 5) + SIZE_BUTTON / 5, SIZE_BUTTON * 2 + SIZE_BUTTON / 5, SIZE_BUTTON);
            }
            mainPanel.add(buttonsNumber[i]);
        }

        JButton buttonEquals = new JButton();
        buttonEquals.setFont(font);
        buttonEquals.setMargin(new Insets(0, 0, 0, 0));
        buttonEquals.setText("=");
        x = 0;
        y = 4;
        buttonEquals.setBounds(x * (SIZE_BUTTON + SIZE_BUTTON / 5) + SIZE_BUTTON / 5, y * (SIZE_BUTTON + SIZE_BUTTON / 5) + SIZE_BUTTON / 5, SIZE_BUTTON * 3 + SIZE_BUTTON / 5 * 2, SIZE_BUTTON);
        buttonEquals.addActionListener(new CharActionListener());
        mainPanel.add(buttonEquals);

        JButton buttonDot = new JButton();
        buttonDot.setFont(font);
        buttonDot.setMargin(new Insets(0, 0, 0, 0));
        buttonDot.setText(",");
        x = 2;
        y = 3;
        buttonDot.setBounds(x * (SIZE_BUTTON + SIZE_BUTTON / 5) + SIZE_BUTTON / 5, y * (SIZE_BUTTON + SIZE_BUTTON / 5) + SIZE_BUTTON / 5, SIZE_BUTTON, SIZE_BUTTON);
        buttonDot.addActionListener(new DigitActionListener());
        mainPanel.add(buttonDot);

        JButton[] buttonsOperation = new JButton[5];
        x = 4;
        for (int i = 0; i < 5; i++) {
            y = i;
            buttonsOperation[i] = new JButton();
            buttonsOperation[i].setFont(font);
            buttonsOperation[i].setMargin(new Insets(0, 0, 0, 0));
            buttonsOperation[i].setText(operation[i]);
            buttonsOperation[i].setBounds(x * (SIZE_BUTTON + SIZE_BUTTON / 5) - SIZE_BUTTON / 5, y * (SIZE_BUTTON + SIZE_BUTTON / 5) + SIZE_BUTTON / 5, SIZE_BUTTON, SIZE_BUTTON);
            buttonsOperation[i].addActionListener(new CharActionListener());
            mainPanel.add(buttonsOperation[i]);
        }

        mainPanel.setBorder(new BevelBorder(1));
        mainPanel.setPreferredSize(new Dimension(WIDTH_FIELD, (SIZE_BUTTON + SIZE_BUTTON / 5) * 5 + SIZE_BUTTON / 5));
        add(mainPanel, BorderLayout.CENTER);
    }

    private void changeResult() {
        textResult.setText(Logic.getResult());
    }

    private void initResultField() {
        resultPanel = new JPanel();
        resultPanel.setLayout(null);
        textResult = new JLabel();
        textResult.setFont(font);
        textResult.setHorizontalAlignment(JLabel.RIGHT);
        textResult.setText(Logic.getResult());
        textResult.setBounds(SIZE_BUTTON / 2, SIZE_BUTTON / 6, SIZE_BUTTON * 5, SIZE_BUTTON);
        resultPanel.add(textResult);
        resultPanel.setBorder(new BevelBorder(1));
        resultPanel.setPreferredSize(new Dimension(WIDTH_FIELD, SIZE_BUTTON + SIZE_BUTTON / 5 * 2));
        add(resultPanel, BorderLayout.NORTH);
    }

    private void initLabel() {
        label = new JLabel();
        label.setFont(font);
        label.setText(" Обычный калькулятор");
        label.setBorder(new BevelBorder(1));
        add(label, BorderLayout.SOUTH);
    }

    private void initFrame() {
        setTitle(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    class DigitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = ((JButton) e.getSource()).getText();
            Logic.addDigit(str);
            changeResult();
            resultPanel.repaint();
        }
    }

    class CharActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = ((JButton) e.getSource()).getText();
            Logic.pressChar(str);
            changeResult();
            resultPanel.repaint();
        }
    }

}