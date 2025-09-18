# Hangman Game

A modern, interactive Hangman word game built with Java Swing featuring a beautiful GUI, virtual keyboard, and visual hangman drawing.

## 🎮 Features

- **Beautiful GUI Interface**: Modern Swing-based user interface with clean design
- **Visual Hangman Drawing**: Progressive hangman drawing that appears with each wrong guess
- **Virtual Keyboard**: Clickable A-Z keyboard with visual feedback for guessed letters
- **Real-time Game State**: Live updates of word progress, remaining guesses, and status
- **Input Validation**: Prevents duplicate guesses and invalid input
- **Win/Lose Dialogs**: Clear game over messages with option to play again
- **Large Word Database**: Uses a comprehensive dictionary with 370,000+ words
- **Cross-platform**: Runs on any system with Java installed

## 🚀 How to Play

1. **Start the Game**: Run the executable or compile and run the Java file
2. **Make Guesses**: 
   - Type letters in the text field and press Enter/Guess button
   - OR click directly on the virtual keyboard letters
3. **Visual Feedback**: 
   - Correct letters appear in the word display
   - Wrong letters darken on the virtual keyboard
   - Hangman drawing progresses with each wrong guess
4. **Win/Lose**: 
   - Win by guessing all letters before the hangman is complete
   - Lose if you make 6 wrong guesses

## 📋 Requirements

- **Java 8 or higher** (JRE/JDK)
- **Windows, macOS, or Linux** (cross-platform)

## 🛠️ Installation & Running

### Option 1: Using the JAR File (Recommended)
```bash
java -jar Hangman.jar
```

### Option 2: Compile from Source
```bash
javac Hangman.java
java Hangman
```

### Option 3: Using Batch File (Windows)
Double-click `Hangman_Launcher.bat` for automatic compilation and execution.

## 📁 Project Structure

```
Hangman/
├── Hangman.java          # Main game source code
├── Dictionary.txt        # Word database (370,000+ words)
├── Hangman.jar          # Executable JAR file
├── Hangman_Launcher.bat # Smart launcher for Windows
├── Hangman.bat          # Simple JAR launcher
└── README.md            # This file
```

## 🎯 Game Mechanics

- **Word Selection**: Randomly selects words from the dictionary
- **Guess Limit**: 6 wrong guesses before game over
- **Letter Revealing**: Correct letters are revealed in their positions
- **Visual Progress**: Hangman drawing shows remaining attempts
- **Keyboard Tracking**: Virtual keyboard shows which letters have been guessed

## 🎨 GUI Components

- **Hangman Drawing Panel**: Custom Graphics2D rendering of the hangman
- **Word Display**: Large, clear font showing current word state
- **Virtual Keyboard**: 3x9 grid of clickable letter buttons
- **Input Field**: Text input with validation
- **Status Messages**: Real-time feedback and game state updates
- **Dialog Boxes**: Win/lose notifications with play-again options

## 🔧 Technical Details

- **Framework**: Java Swing (JFrame, JPanel, JButton, etc.)
- **Graphics**: Custom Graphics2D rendering for hangman drawing
- **Event Handling**: ActionListeners for button clicks and text input
- **Layout Management**: BorderLayout and GridLayout for organized UI
- **File I/O**: Scanner for reading dictionary file
- **Data Structures**: ArrayList for word storage and guess tracking

## 🎮 Controls

- **Text Input**: Type letters and press Enter or click Guess button
- **Virtual Keyboard**: Click any letter button to make a guess
- **Game Reset**: Automatic restart option after win/lose
- **Exit**: Close window or select "No" when asked to play again

## 📝 Development

Built with modern Java practices:
- Object-oriented design with custom JPanel classes
- Event-driven programming with ActionListeners
- Clean separation of concerns (GUI, game logic, drawing)
- Proper error handling and user feedback
- Cross-platform compatibility

## 🏆 Enjoy the Game!

Have fun playing this classic word game with a modern twist! The combination of visual feedback, intuitive controls, and beautiful graphics makes for an engaging gaming experience.
