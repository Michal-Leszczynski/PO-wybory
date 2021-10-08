// Klasa reprezentująca kandydata.
// Kandydat rozszerza klasę obywatela.
public class Candidate extends Citizen {
    private final String party;     // Nazwa partii, do której należy kandydat.
    private int listNr;             // Numer kandydata na liście swojej partii.
    private final int[] attributes; // Tablica zawierająca wartości cech kandydata.
    private int votes;              // Ilość otrzymanych przez kandydata głosów.

    // Getter nazwy partii kandydata.
    public String getParty() {
        return this.party;
    }

    // Getter ilości cech posiadanych przez kandydata.
    public int getAttributeNr(int nr) {
        return this.attributes[nr];
    }

    // Getter ilości otrzymanych głosów przez kandydata.
    public int getVotes() {
        return this.votes;
    }

    // Konstruktor.
    public Candidate(String name, String surname, int constituencyNr, String party, int listNr, int[] attributes) {
        super(name, surname, constituencyNr);
        this.party = party;
        this.listNr = listNr;
        this.attributes = attributes;
        this.votes = 0;
    }

    // Metoda, która zwiększa numeru kandydata na liście partii o zadaną wartość.
    public void addListNr(int addListNr) {
        this.listNr += addListNr;
    }

    // Metoda, która zwiększa ilość otrzymanych głosów przez kandydata o jeden.
    public void voted() {
        this.votes++;
    }

    // Metoda, która wczytuje parametry kandydata oraz tworzy obiekt im odpowiadający.
    public static Candidate readCandidate(Reading reading, int attributesAmount) {
        String name = reading.readString();
        String surname = reading.readString();
        int constituencyNr = reading.readInt();
        String party = reading.readString();
        int listNr = reading.readInt();
        int[] attributes = new int[attributesAmount];

        for (int i = 0; i < attributesAmount; i++) {
            attributes[i] = reading.readInt();
        }

        return new Candidate(name, surname, constituencyNr, party, listNr, attributes);
    }

    // Metoda, która wypisuje imię, nazwisko, partię, numer na liście oraz
    // liczbę otrzymanych głosów przez kandydata.
    public void writeCandidate() {
        System.out.println("Kandydat: " + this.name + " " + this.surname + " " + this.party + " " + this.listNr + " " + this.votes + " ");
    }
}
