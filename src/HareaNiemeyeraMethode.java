import java.util.ArrayList;
import java.util.Random;

// Klasa reprezentująca metodę Hare’a-Niemeyera przeliczania głosów na mandaty.
// Implementuje interfejs CountingVotesMethod.
public class HareaNiemeyeraMethode implements CountingVotesMethod {

    // Metoda, która wypisuje nazwę danej metody przeliczania głosów na mandaty.
    @Override
    public void writeMethodName() {
        System.out.println("Metoda Hare’a-Niemeyera");
    }

    // Metoda Hare’a-Niemeyera.
    @Override
    public int[] calculateMandates(int[] votes, int mandatesAmount) {
        int[] result = new int[votes.length];
        Random random = new Random();
        ArrayList<Integer> challengers = new ArrayList<Integer>();
        double[] remainders = new double[votes.length];
        double totalVotes = 0.0;
        int givenMandates = 0;

        for (int i = 0; i < votes.length; i++) {
            totalVotes += (double)votes[i];
        }

        for (int i = 0; i < votes.length; i++) {
            double tempDouble = (double)votes[i] * (double)mandatesAmount / totalVotes;
            result[i] = (int)Math.floor(tempDouble);
            remainders[i] = tempDouble - (double)result[i];
            givenMandates += result[i];
        }

        for (int i = givenMandates; i < mandatesAmount; i++) {
            double max = -1.0;

            for (int j = 0; j < remainders.length; j++) {
                if (remainders[j] > max) {
                    challengers.clear();
                    challengers.add(j);
                    max = remainders[j];
                }
                else if (remainders[j] == max) {
                    challengers.add(j);
                }
            }

            while (i < mandatesAmount && !challengers.isEmpty()) {
                int rand = random.nextInt(challengers.size());
                int most = challengers.get(rand);
                result[most]++;
                remainders[most] = -1.0;
                challengers.remove(rand);
                i++;
            }
        }

        return result;
    }

}