// Klasa reprezentująca wyborcę typu maksymalizujący jednocechowy wybierający spośród wszystkich partii.
// Rozszerza klasę wyborca oraz korzysta z interfejsu OneAttribute.
public class MaximizingOneAttributeAllParties extends Voter implements OneAttribute {
    private final int attributeNr; // Numer cechy, według której będzie wybierany kandydat.

    // Konstruktor.
    public MaximizingOneAttributeAllParties(String name, String surname, int constituencyNr, int type, int attributeNr) {
        super(name, surname, constituencyNr, type);
        this.attributeNr = attributeNr;
    }

    // Metoda, która na podstawie otrzymanych oraz wczytanych parametrów zwraca obiekt klasy wyborca typu maksymalizujący jednocechowy wybierający spośród wszystkich partii.
    public static Voter readMinimizingOneAttributeAllParties(Reading reading, String name, String surname,  int constituencyNr, int type) {
        int attributeNr = reading.readInt();

        return new MaximizingOneAttributeAllParties(name, surname, constituencyNr, type, attributeNr);
    }

    // Metoda wybierająca ustalonego kandydata o najwyższej wartości ustalonej cechy z zadanej tablicy kandydatów.
    @Override
    public Candidate chooseCandidate(Candidate[] candidates) {
        return OneAttribute.chooseCandidateOneAttribute(0, candidates.length - 1, 1, candidates, this.attributeNr);
    }
}
