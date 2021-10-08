import java.util.ArrayList;
import java.util.Random;

// Interfejs zawierający metody związane z liczeniem sum ważonych.
public interface Universal {

    // Metoda, która dla zadanej tablicy wag oraz kandydata, liczy jego sumę ważoną cech.
    public static int calculateSumWeights(int[] weights, Candidate candidate) {
        int sumAttributes = 0;

        for (int i = 0; i < weights.length; i++) {
            sumAttributes += weights[i] * candidate.getAttributeNr(i);
        }

        return sumAttributes;
    }

    // Metoda, która dla zadanej tablicy kandydatów wybiera tego, na którego zostanie oddany głos.
    // Wybiera spośród kandydatów, których index w tablicy jest w przedziale [start, end],
    // tego, który ma największą sumę ważoną cech.
    public static Candidate chooseCandidateUniversal(int start, int end, int[] weights, Candidate[] candidates) {
        Random random = new Random();
        ArrayList<Candidate> challengers = new ArrayList<Candidate>();
        int actual = -1000005;

        for (int i = start; i <= end; i++) {
            int temp = calculateSumWeights(weights, candidates[i]);

            if (temp > actual) {
                challengers.clear();
                challengers.add(candidates[i]);
                actual = temp;
            }
            else if (temp == actual) {
                challengers.add(candidates[i]);
            }
        }

        return challengers.get(random.nextInt(challengers.size()));
    }

    // Metoda, która używa danego działania na zadanej tablicy wag wyborcy.
    public static void tryActivity(int[] activity, int[] weights) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] += activity[i];

            if (weights[i] < -100) {
                weights[i] = - 100;
            }
            else if (weights[i] > 100) {
                weights[i] = 100;
            }
        }
    }
}
