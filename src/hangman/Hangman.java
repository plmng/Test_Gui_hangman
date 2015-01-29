package hangman;

import javax.swing.SwingUtilities;

import model.HangmanModel;
import view.HangmanFrame;

public class Hangman implements Runnable {
	public void run() {
		new HangmanFrame(new HangmanModel());
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Hangman());
    }
}