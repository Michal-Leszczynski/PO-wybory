import java.util.Scanner;

// Klasa reprezentująca obiekt odpowiedzialny za wczytywanie danych.
public class Reading {
    private Scanner in;      // Skaner.
    private String[] toRead; // Tablica zawierające stringi, które kolejno będą przetwarzane jako dane wejściowe.
    private int index;       // Index odpowiadający Stringowi, który powinien zostać teraz przetworzony.

    // Konstruktor.
    public Reading() {
        this.in = new Scanner(System.in);
        this.toRead = new String[0];
        this.index = 0;
    }

    // Metoda sprawdzająca, czy zostały jeszcze dane do przetworzenie.
    // Jeśli takowe nie istnieją, wczytuje nową linię, po czym dzieli ją
    // po białych znakach na Stringi, które będą następnie przetwarzane jako
    // dane wejściowe. Zwraca kolejnego Stringa do przetworzenia.
    public String readString() {
        if (this.index == this.toRead.length) {
            this.toRead = this.in.nextLine().trim().split("\\s+");
            this.index = 0;
        }

        this.index++;
        return toRead[index - 1];
    }

    // Metoda, która wczytuje Stringa za pomocą metody readString()
    // i zwraca go po konwersji na inta.
    public int readInt() {
        return  Integer.parseInt(this.readString());
    }

    // Metoda, która wczytuje za Stringa postaci "(a,b)", gdzie a i b to dowolne inty,
    // za pomocą metod readString(). Zwraca inta odpowiadającego wartości a.
    public int readFirstIntOfPair() {
        String tempString = readString();
        int tempIndex = 0;

        for ( ; tempIndex < tempString.length(); tempIndex++) {
            if (tempString.charAt(tempIndex) == ',') {
                break;
            }
        }

        return  Integer.parseInt(tempString.substring(1, tempIndex));
    }

    // Metoda, która zamyka skaner będący atrybutem obiektu.
    public void closeScanner() {
        this.in.close();
    }
}