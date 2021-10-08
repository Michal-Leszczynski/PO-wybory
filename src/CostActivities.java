// Interfejs zawierający metodę wyboru działania oraz okręgu przez partie działające skromnie oraz z rozmachem.
public interface CostActivities {

    // Metoda, która wybiera numer okręgu oraz działania tak, aby jego zastosowanie
    // mieściło się w budżecie oraz jego koszt był jak największy (kind = 1)
    // lub jak najmniejszy (kind = -1).
    public static int[] chooseAct(Constituency[] constituencies, ElectoralActivities acts, int kind, int budget) {
        int[] result = new int[2];
        result[0] = -1;
        int actual = -1000005 * kind;

        for (int i = 0; i < constituencies.length; i++) {
            if (!constituencies[i].getIfMerged()) {
                for (int j = 0; j < acts.getActivities().length; j++) {
                    int temp = acts.ActivityCost(j, constituencies[i]);

                    if (budget >= temp && temp * kind >= actual * kind) {
                        result[0] = i;
                        result[1] = j;
                        actual = temp;
                    }
                }
            }
        }

        return result;
    }
}
