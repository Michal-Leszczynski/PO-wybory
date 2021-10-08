// Klasa reprezentująca wyborcę typu wszechstronny wybierający spośród wszystkich partii.
// Rozszerza klasę wyborca oraz korzysta z interfejsu Universal.
public class UniversalAllParties extends Voter implements Universal {
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
    public UniversalAllParties(String name, String surname, int constituencyNr, int type, int[] weights) {
        super(name, surname, constituencyNr, type);
        this.weights = weights;
        this.tempWeights = new int[weights.length];
    }

    // Metoda, która na podstawie otrzymanych oraz wczytanych parametrów zwraca obiekt klasy wyborca typu wszechstronny wybierający spośród wszystkich partii.
    public static Voter readUniversalAllParties(Reading reading, String name, String surname, int constituencyNr, int type, int attributesAmount) {
        int[] weights = new int[attributesAmount];

        for (int i = 0; i < attributesAmount; i++) {
            weights[i] = reading.readInt();
        }

        return new UniversalAllParties(name, surname, constituencyNr, type, weights);
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

    // Metoda wybierająca kandydata o najwyższej sumie ważonej cech z zadanej tablicy kandydatów.
    @Override
    public Candidate chooseCandidate(Candidate[] candidates) {
        return Universal.chooseCandidateUniversal(0, candidates.length - 1, this.weights, candidates);
    }
}
