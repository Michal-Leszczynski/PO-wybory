import java.util.Random;

// Klasa reprezentująca żelazny elektorat partyjny.
// Rozszerza klasę wyborca.
public class IronElectorateParty extends Voter {
    protected final String party; // Nazwa partii.

    // Konstruktor.
    public IronElectorateParty(String name, String surname, int constituencyNr, int type, String party) {
        super(name, surname, constituencyNr, type);
        this.party = party;
    }

    // Metoda, która na podstawie otrzymanych oraz wczytanych parametrów zwraca obiekt klasy żelazny elektorat partyjny.
    public static Voter readIronElectorateParty(Reading reading, String name, String surname, int constituencyNr, int type) {
        String party = reading.readString();

        return new IronElectorateParty(name, surname, constituencyNr, type, party);
    }

    // Metoda wybierająca losowego kandydata z danej partii z zadanej tablicy kandydatów.
    @Override
    public Candidate chooseCandidate(Candidate[] candidates) {
        Random random = new Random();
        int start = ListPositions.partyStartingPosition(candidates, this.party);
        int amount = ListPositions.partyEndingPosition(candidates, this.party) - start + 1;
        int choice = random.nextInt(amount);

        return candidates[start + choice];
    }
}
