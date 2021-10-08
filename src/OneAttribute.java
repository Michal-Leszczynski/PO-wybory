import java.util.ArrayList;
import java.util.Random;

// Interfejs zawierający metodę, służącą do wyboru kandydata przez wyborców typu 3, 4, 6, 7.
public interface OneAttribute {

    // Metoda, która z zadanej tablicy kandydatów wybiera tego, na którego zostanie oddany głos.
    // Wybiera spośród kandydatów, których index w tablicy jest w przedziale [start, end],
    // tego, który ma najmniejszą/największą wartość cechy o indexie attributeNr.
    // dla kind = 1 wybiera największą, a dla kind = -1 wybiera najmniejszą.
    public static Candidate chooseCandidateOneAttribute(int start, int end, int kind, Candidate[] candidates, int attributeNr) {
        attributeNr--;
        Random random = new Random();
        ArrayList<Candidate> challengers = new ArrayList<Candidate>();
        int temp = -101 * kind;

        for (int i = start; i <= end; i++) {
            if (candidates[i].getAttributeNr(attributeNr) * kind > temp * kind) {
                challengers.clear();
                challengers.add(candidates[i]);
                temp = candidates[i].getAttributeNr(attributeNr);
            }
            else if (candidates[i].getAttributeNr(attributeNr) == temp) {
                challengers.add(candidates[i]);
            }
        }

        return challengers.get(random.nextInt(challengers.size()));
    }
}
