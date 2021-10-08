// Interfejs zawierający metody służące do określenia pozycji kandydatów danej partii w tablicy.
public interface ListPositions {

    // Metoda, która zwraca index pierwszego kandydata w zadanej tablicy, który
    // należy do zadanej partii.
    public static int partyStartingPosition(Candidate[] candidates, String party) {
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i].getParty().equals(party)) {
                return i;
            }
        }

        return 0;
    }

    // Metoda, która zwraca index ostatniego kandydata w zadanej tablicy, który
    // należy do zadanej partii.
    public static int partyEndingPosition(Candidate[] candidates, String party) {
        for (int i = candidates.length - 1; i >= 0; i--) {
            if (candidates[i].getParty().equals(party)) {
                return i;
            }
        }

        return 0;
    }
}
