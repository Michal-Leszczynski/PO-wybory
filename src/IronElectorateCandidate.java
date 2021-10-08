// Klasa reprezentująca żelazny elektorat kandydata.
// Rozszerza klasę żelazny elektorat partyjny.
public class IronElectorateCandidate extends IronElectorateParty {
    private final int listPosition; // Pozycja kandydata na liście

    // Konstrukotr.
    public IronElectorateCandidate(String name, String surname, int constituencyNr, int type, String party, int listPosition) {
        super(name, surname, constituencyNr, type, party);
        this.listPosition = listPosition;
    }

    // Metoda, która na podstawie otrzymanych oraz wczytanych parametrów zwraca obiekt klasy żelazny elektorat kandydata.
    public static Voter readIronElectorateCandidate(Reading reading, String name, String surname,  int constituencyNr, int type) {
        String party = reading.readString();
        int listPosition = reading.readInt();

        return new IronElectorateCandidate(name, surname, constituencyNr, type, party, listPosition);
    }

    // Metoda wybierająca ustalonego kandydata (ustalona partia oraz numer na liście) z zadanej tablicy kandydatów.
    @Override
    public Candidate chooseCandidate(Candidate[] candidates) {
        int start = ListPositions.partyStartingPosition(candidates, this.party);

        return candidates[start + this.listPosition - 1];
    }
}
