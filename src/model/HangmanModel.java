package model;
 
import java.util.ArrayList;
import java.util.List;
 
import view.HangmanPanel;
 
public class HangmanModel {
    private int             maximumWrongGuesses;
    private int             numberOfGuesses;
    private int             wrongGuesses;
    private List<Character>   unguessedLetters;
    private Phrase          phrase;
    private String          currentPhrase;
    private String          hiddenPhrase;
 
    public HangmanModel() {
        this.phrase = new Phrase();
        init();
    }
 
    public void init() {
        this.numberOfGuesses = 0;
        this.wrongGuesses = 0;
        this.maximumWrongGuesses = HangmanPanel.maximumWrongGuesses;
        this.unguessedLetters = resetLettersGuessed();
        this.currentPhrase = phrase.getPhrase();
        this.hiddenPhrase = phrase.getHiddenPhrase();
    }
 
    private List<Character> resetLettersGuessed() {
        List<Character> unguessedLetters = new ArrayList<Character>();
        for (int i = 0; i < 26; i++) {
            Character c = (char) (i + 'A');
            unguessedLetters.add(c);
        }
        return unguessedLetters;
    }
 
    public boolean isPossibleLetter(String letter) {
        String u = letter.toUpperCase();
        Character c = u.charAt(0);
        return unguessedLetters.contains(c);
    }
 
    public void guessLetter(String letter) {
        String l = letter.toLowerCase();
        String u = letter.toUpperCase();
 
        StringBuilder builder = new StringBuilder();
        boolean incorrectGuess = true;
        for (int i = 0; i < currentPhrase.length(); i++) {
            String s = currentPhrase.substring(i, i + 1);
            if (l.equals(s.toLowerCase())) {
                builder.append(s);
                incorrectGuess = false;
            } else {
                builder.append(hiddenPhrase.charAt(i));
            }
        }
 
        numberOfGuesses++;
 
        if (incorrectGuess) {
            wrongGuesses++;
        }
 
        Character c = u.charAt(0);
        unguessedLetters.remove(c);
 
        hiddenPhrase = builder.toString();
    }
 
    public boolean isDead() {
        return wrongGuesses >= maximumWrongGuesses;
    }
 
    public boolean isSolved() {
        boolean isSolved = true;
 
        for (int i = 0; isSolved && i < hiddenPhrase.length(); i++) {
            char c = hiddenPhrase.charAt(i);
            if (c == '_') {
                isSolved = false;
            }
        }
 
        return isSolved;
    }
 
    public String getUnguessedLetters() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < unguessedLetters.size(); i++) {
            builder.append(unguessedLetters.get(i));
            if (i < (unguessedLetters.size() - 1)) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
 
    public String getNumberOfGuesses() {
        return Integer.toString(numberOfGuesses);
    }
 
    public List<String> getHiddenPhrase() {
        List<String> lines = new ArrayList<String>();
 
        int size = 12;
        String s = hiddenPhrase;
 
        while (s.length() > size) {
            int pos = 0;
            while (pos >= 0 && pos < size) {
                pos = s.indexOf(" ", pos + 1);
            }
            if (pos >= 0) {
                String t = s.substring(0, pos);
                lines.add(expandHiddenPhrase(t));
                s = s.substring(pos + 1);
            } else {
                break;
            }
        }
 
        lines.add(expandHiddenPhrase(s));
 
        return lines;
    }
 
    private String expandHiddenPhrase(String phrase) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            builder.append(phrase.charAt(i));
            if (i < (phrase.length() - 1)) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
 
    public int getWrongGuesses() {
        return wrongGuesses;
    }
 
    public String getCurrentPhrase() {
        return currentPhrase;
    }
 
}