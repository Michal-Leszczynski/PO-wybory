import java.util.ArrayList;
import java.util.Random;

// Klasa reprezentująca metodę Sainte-Laguë przeliczania głosów na mandaty.
// Implementuje interfejs CountingVotesMethod.
public class SainteLaguëMethode implements CountingVotesMethod {

    // Metoda, która wypisuje nazwę danej metody przeliczania głosów na mandaty.
    @Override
    public void writeMethodName() {
        System.out.println("Metoda Sainte-Laguë");
    }

    // Metoda Sainte-Laguë.
    @Override
    public int[] calculateMandates(int[] votes, int mandatesAmount) {
        int[] result = new int[votes.length];
        int[] dividers = new int[votes.length];
        Random random = new Random();
        ArrayList<Integer> challengers = new ArrayList<Integer>();
        int[] actualValue = votes.clone();
        int[] startingValues = votes.clone();

        for (int j = 0; j < mandatesAmount;) {
            int max = -1000005;

            for (int i = 0; i < actualValue.length; i++) {
                if (actualValue[i] > max) {
                    challengers.clear();
                    challengers.add(i);
                    max = actualValue[i];
                }
                else if (actualValue[i] == max) {
                    challengers.add(i);
                }
            }

            while (j < mandatesAmount && !challengers.isEmpty()) {
                int rand = random.nextInt(challengers.size());
                int most = challengers.get(rand);
                result[most]++;
                dividers[most] += 2;
                actualValue[most] = startingValues[most] / (dividers[most] + 1);
                challengers.remove(rand);
                j++;
            }
        }

        return result;
    }
}
