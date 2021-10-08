// Interfejs zawierający metodę wyboru działania oraz okręgu przez partie zachłanne oraz złośliwe (mój własny pomysł).
public interface GreedyActivities {

    // Metoda, która wybiera numer okręgu oraz działania tak, aby jego zastosowanie
    // mieściło się w budżecie oraz zmiana sumy ważonej cech kandydatów należących do zadanej partii (ifParty = true)
    // lub kandydatów nienależących do niej (ifParty = false) była jak największa (kind = 1) lub jak najmniejsza (kind = -1).
    public static int[] chooseAct(Constituency[] constituencies, ElectoralActivities acts, String party, int kind, boolean ifParty, int budget) {
        int[] result = new int[2];
        result[0] = -1;
        int actual = -1000005 * kind;

        for (int i = 0; i < constituencies.length; i++) {
            if (!constituencies[i].getIfMerged()) {
                for (int j = 0; j < acts.getActivities().length; j++) {
                    if (budget >= acts.ActivityCost(j, constituencies[i])) {
                        int temp = acts.calculateDeltaSumWeights(constituencies[i], party, ifParty, j);

                        if (temp * kind >= actual * kind) {
                            result[0] = i;
                            result[1] = j;
                            actual = temp;
                        }
                    }
                }
            }
        }

        return result;
    }
}
