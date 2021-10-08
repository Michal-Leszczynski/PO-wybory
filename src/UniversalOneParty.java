// Klasa reprezentująca wyborcę typu wszechstronny wybierający spośród jednej partii.
// Rozszerza klasę wyborca oraz korzysta z interfejsu Universal.
public class UniversalOneParty extends IronElectorateParty implements Universal {
    private int[] weights;     // tablica wag cech wyborcy.
    private int[] tempWeights; // tablica tymczasowych wag cech, powstała w wyniku zastosowania działania na tablicy wag cech.

    // Getter tablicy tymczasowych wag cech wyborcy.
    @Override
    public int[] getTempWeights() {
        return this.tempWeights;
    }

    // Getter tablicy wag cech wyborcy.
    @Override
    public int[] getWeights() {
        return this.weights;
    }

    // Konstrukotr.
    public UniversalOneParty(String name, String surname, int constituencyNr, int type, int[] weights, String party) {
        super(name, surname, constituencyNr, type, party);
        this.weights = weights;
        this.tempWeights = new int[weights.length];
    }

    // Metoda, która na podstawie otrzymanych oraz wczytanych parametrów zwraca obiekt klasy wyborca typu wszechstronny wybierający spośród jednej partii.
    public static Voter readUniversalOneParty(Reading reading, String name, String surname, int constituencyNr, int type, int attributesAmount) {
        int[] weights = new int[attributesAmount];

        for (int i = 0; i < attributesAmount; i++) {
            weights[i] = reading.readInt();
        }

        String party = reading.readString();

        return new UniversalOneParty(name, surname, constituencyNr, type, weights, party);
    }

    // Metoda, która ustawia tablicę wag tymczasowych tak,
    // aby reprezentowała ona tablicę wag po dokonaniu pewnego działąnia wyborczego.
    @Override
    public void tryActivity(int[] activity) {
        this.tempWeights = this.weights.clone();
        Universal.tryActivity(activity, this.tempWeights);
    }

    // Metoda, która ustawia tablicę wag wyborcy na jego tablicę wag tymczasowych.
    @Override
    public void setActivity() {
        this.weights = this.tempWeights;
    }

    // Metoda wybierająca kandydata należącego do ustalonej partii o najwyższej sumie ważonej cech z zadanej tablicy kandydatów.
    @Override
    public Candidate chooseCandidate(Candidate[] candidates) {
        int start = ListPositions.partyStartingPosition(candidates, this.party);
        int end = ListPositions.partyEndingPosition(candidates, this.party);

        return Universal.chooseCandidateUniversal(start, end, this.weights, candidates);
    }
}
