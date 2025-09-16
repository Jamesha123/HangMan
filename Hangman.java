import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hangman extends JFrame {
    private String word;
    private List<Character> playerGuesses;
    private int wrongCount;
    private JLabel wordLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel statusLabel;
    private HangmanPanel hangmanPanel;
    private KeyboardPanel keyboardPanel;
    private List<String> words;
    private boolean gameOver;

    public Hangman() throws FileNotFoundException {
        initializeGame();
        setupGUI();
        startNewGame();
    }

    private void initializeGame() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("D:/Game Projects/Hangman/Dictionary.txt"));
        words = new ArrayList<String>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
        scanner.close();
    }

    private void setupGUI() {
        setTitle("Hangman Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create hangman drawing panel
        hangmanPanel = new HangmanPanel();
        add(hangmanPanel, BorderLayout.CENTER);

        // Create keyboard panel
        keyboardPanel = new KeyboardPanel();
        add(keyboardPanel, BorderLayout.SOUTH);

        // Create control panel
        JPanel controlPanel = new JPanel(new BorderLayout());
        
        // Word display
        wordLabel = new JLabel("", JLabel.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        wordLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        controlPanel.add(wordLabel, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter a letter:"));
        guessField = new JTextField(5);
        guessField.addActionListener(new GuessActionListener());
        inputPanel.add(guessField);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessActionListener());
        inputPanel.add(guessButton);
        controlPanel.add(inputPanel, BorderLayout.CENTER);

        // Status label
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        controlPanel.add(statusLabel, BorderLayout.SOUTH);

        add(controlPanel, BorderLayout.NORTH);

        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startNewGame() {
        Random rand = new Random();
        word = words.get(rand.nextInt(words.size()));
        playerGuesses = new ArrayList<>();
        wrongCount = 0;
        gameOver = false;
        updateWordDisplay();
        updateHangman();
        updateKeyboard();
        statusLabel.setText("Make your first guess!");
        guessField.setText("");
        guessField.requestFocus();
    }

    private void updateWordDisplay() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                display.append(word.charAt(i));
            } else {
                display.append("-");
            }
            if (i < word.length() - 1) {
                display.append(" ");
            }
        }
        wordLabel.setText(display.toString());
    }

    private void updateHangman() {
        hangmanPanel.setWrongCount(wrongCount);
        hangmanPanel.repaint();
    }

    private void updateKeyboard() {
        keyboardPanel.updateGuessedLetters(playerGuesses);
    }

    private void makeGuess() {
        if (gameOver) return;

        String input = guessField.getText().trim().toLowerCase();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            statusLabel.setText("Please enter a single letter!");
            return;
        }

        char guess = input.charAt(0);
        if (playerGuesses.contains(guess)) {
            statusLabel.setText("You already guessed that letter!");
            return;
        }

        playerGuesses.add(guess);
        guessField.setText("");

        if (!word.contains(String.valueOf(guess))) {
            wrongCount++;
            statusLabel.setText("Wrong guess! " + (6 - wrongCount) + " guesses left.");
        } else {
            statusLabel.setText("Correct guess!");
        }

        updateWordDisplay();
        updateHangman();
        updateKeyboard();

        // Check win condition
        boolean won = true;
        for (int i = 0; i < word.length(); i++) {
            if (!playerGuesses.contains(word.charAt(i))) {
                won = false;
                break;
            }
        }

        if (won) {
            gameOver = true;
            JOptionPane.showMessageDialog(this, "Congratulations! You won!\nThe word was: " + word, 
                                        "You Win!", JOptionPane.INFORMATION_MESSAGE);
            askPlayAgain();
        } else if (wrongCount >= 6) {
            gameOver = true;
            JOptionPane.showMessageDialog(this, "Game Over! You lost!\nThe word was: " + word, 
                                        "You Lose!", JOptionPane.INFORMATION_MESSAGE);
            askPlayAgain();
        }
    }

    private void askPlayAgain() {
        int choice = JOptionPane.showConfirmDialog(this, "Would you like to play again?", 
                                                 "Play Again?", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            startNewGame();
        } else {
            System.exit(0);
        }
    }

    private class GuessActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            makeGuess();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Hangman();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Dictionary file not found!", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Custom JPanel for drawing the hangman
    private class HangmanPanel extends JPanel {
        private int wrongCount = 0;

        public void setWrongCount(int wrongCount) {
            this.wrongCount = wrongCount;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(Color.BLACK);

            int width = getWidth();
            int height = getHeight();
            int centerX = width / 2;
            int startY = height - 50;

            // Draw gallows
            g2d.drawLine(centerX - 80, startY, centerX + 80, startY); // base
            g2d.drawLine(centerX, startY, centerX, startY - 200); // pole
            g2d.drawLine(centerX, startY - 200, centerX + 60, startY - 200); // top
            g2d.drawLine(centerX + 60, startY - 200, centerX + 60, startY - 180); // rope

            // Draw hangman based on wrong count
            if (wrongCount >= 1) {
                // Head
                g2d.drawOval(centerX + 50, startY - 170, 20, 20);
            }
            
            if (wrongCount >= 2) {
                // Body
                g2d.drawLine(centerX + 60, startY - 150, centerX + 60, startY - 100);
            }
            
            if (wrongCount >= 3) {
                // Left arm
                g2d.drawLine(centerX + 60, startY - 130, centerX + 40, startY - 110);
            }
            
            if (wrongCount >= 4) {
                // Right arm
                g2d.drawLine(centerX + 60, startY - 130, centerX + 80, startY - 110);
            }
            
            if (wrongCount >= 5) {
                // Left leg
                g2d.drawLine(centerX + 60, startY - 100, centerX + 40, startY - 70);
            }
            
            if (wrongCount >= 6) {
                // Right leg
                g2d.drawLine(centerX + 60, startY - 100, centerX + 80, startY - 70);
            }
        }
    }

    // Custom JPanel for the virtual keyboard
    private class KeyboardPanel extends JPanel {
        private JButton[] letterButtons;
        private List<Character> guessedLetters;

        public KeyboardPanel() {
            setLayout(new GridLayout(3, 9, 5, 5));
            setBorder(BorderFactory.createTitledBorder("Virtual Keyboard"));
            setBackground(Color.LIGHT_GRAY);
            
            letterButtons = new JButton[26];
            guessedLetters = new ArrayList<>();
            
            // Create buttons for A-Z
            for (char c = 'A'; c <= 'Z'; c++) {
                JButton button = new JButton(String.valueOf(c));
                button.setFont(new Font("Arial", Font.BOLD, 14));
                button.setPreferredSize(new Dimension(40, 40));
                button.setBackground(Color.WHITE);
                button.setFocusPainted(false);
                
                // Add action listener
                char letter = c;
                button.addActionListener(e -> {
                    if (!gameOver && !guessedLetters.contains(Character.toLowerCase(letter))) {
                        guessField.setText(String.valueOf(letter).toLowerCase());
                        makeGuess();
                    }
                });
                
                letterButtons[c - 'A'] = button;
                add(button);
            }
        }

        public void updateGuessedLetters(List<Character> guesses) {
            guessedLetters = new ArrayList<>(guesses);
            
            for (int i = 0; i < 26; i++) {
                char letter = (char) ('A' + i);
                JButton button = letterButtons[i];
                
                if (guessedLetters.contains(Character.toLowerCase(letter))) {
                    // Darken the button for guessed letters
                    button.setBackground(Color.DARK_GRAY);
                    button.setForeground(Color.WHITE);
                    button.setEnabled(false);
                } else {
                    // Reset button appearance for unguessed letters
                    button.setBackground(Color.WHITE);
                    button.setForeground(Color.BLACK);
                    button.setEnabled(true);
                }
            }
        }
    }
}