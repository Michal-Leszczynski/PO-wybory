// Klasa reprezentująca wyborcę typu maksymalizujący jednocechowy wybierający spośród jednej partii.
// Rozszerza klasę wyborca oraz korzysta z interfejsu OneAttribute.
public class MaximizingOneAttributeOneParty extends IronElectorateParty implements OneAttribute {
    private final int attributeNr; // Numer cechy, według której będzie wybierany kandydat.

    // Konstruktor.
    public MaximizingOneAttributeOneParty(String name, String surname, int constituencyNr, int type, int attributeNr, String party) {
        super(name, surname, constituencyNr, type, party);
        this.attributeNr = attributeNr;
    }

    // Metoda, która na podstawie otrzymanych oraz wczytanych parametrów zwraca obiekt klasy wyborca typu maksymalizujący jednocechowy wybierający spośród jednej partii.
    public static Voter readMaximizingOneAttributeOneParty(Reading reading, String name, String surname,  int constituencyNr, int type) {
        int attributeNr = reading.readInt();
        String party = reading.readString();

        return new MaximizingOneAttributeOneParty(name, surname, constituencyNr, type, attributeNr, party);
    }

    // Metoda wybierająca ustalonego kandydata należącego do ustalonej partii o najwyższej wartości ustalonej cechy z zadanej tablicy kandydatów.
    @Override
    public Candidate chooseCandidate(Candidate[] candidates) {
        int start = ListPositions.partyStartingPosition(candidates, this.party);
        int end = ListPositions.partyEndingPosition(candidates, this.party);

        return OneAttribute.chooseCandidateOneAttribute(start, end, 1, candidates, this.attributeNr);
    }
}
