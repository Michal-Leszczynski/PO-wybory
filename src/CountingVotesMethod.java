// Interfejs zawierający metodą zamiany głosów na mandaty.
public interface CountingVotesMethod {

    // Metoda, która wypisuje nazwę danej metody przeliczania głosów na mandaty.
    public abstract void writeMethodName();

    // Metoda, która dla tablicy reprezentującej przyznane głosy każdej partii
    // oraz ilości mandatów do rozdania, rozdaje je. Wynik zwraca w formie tablicy,
    // gdzie kolejne wartości to ilości mandatów przyznanych odpowiednim partiom.
    public abstract int[] calculateMandates(int[] votes, int mandatesAmount);
}
