# Hangman Game

Interactive Hangman word game built with Java Swing featuring a GUI, virtual keyboard, and visual hangman drawing.

## 🎮 Features

- **GUI Interface**: Swing-based user interface with clean design
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
``
Enjoy playing HangMan!
