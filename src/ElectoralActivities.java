// Klasa reprezentująca działania wyborcze.
public class ElectoralActivities {
    private final int[][] activities; // Tablica reprezentująca działania wyborcze (każde z nich reprezentowane jako tablica zmian cech).
    private final int[] sumActivity;  // Tablica reprezentująca sumę bezwzględnych wartości zmian wag działania.

    // Getter tablicy działań wyborczych.
    public int[][] getActivities() { return this.activities; }

    // Konstruktor.
    public ElectoralActivities(int[][] activities, int[] sumActivity) {
        this.activities = activities;
        this.sumActivity = sumActivity;
    }

    // Metoda, która na wczytuje odpowiednie parametry oraz na ich podstawie tworzy
    // i zwraca obiekt klasy działania wyborcze.
    public static ElectoralActivities readElectoralActivities(Reading reading, int attributesAmount, int activitiesAmount) {
        int[][] tempActivities = new int[activitiesAmount][attributesAmount];
        int[] sumActivity = new int[activitiesAmount];

        for (int i = 0; i < activitiesAmount; i++) {
            int tempSumActivity = 0;

            for (int j = 0; j < attributesAmount; j++) {
                tempActivities[i][j] = reading.readInt();
                tempSumActivity += Math.abs(tempActivities[i][j]);
            }

            sumActivity[i] = tempSumActivity;
        }

        return new ElectoralActivities(tempActivities, sumActivity);
    }

    // Metoda, która oblicza koszt zastosowania działania wyborczego
    // dla zadanego numeru działania oraz okręgu.
    public int ActivityCost(int activityNr, Constituency constituency) {
        return this.sumActivity[activityNr] * constituency.getVotersAmount();
    }

    // Metoda, która oblicza różnicę sum ważonych cech kandydatów na podstawie wag cech wyborców.
    // Różnica brana jest między doraźnymi wagami cech wyborców a tymi, które powstaną w wyniku
    // zastosowania zadanego działania. Sumy są liczone na podstawie kandydatów należących do zadanej
    // partii (ifPart = true) lub do tych, którzy do niej nie należą (ifParty = false).
    public int calculateDeltaSumWeights(Constituency constituency, String party, boolean ifParty, int activityNr) {
        Candidate[] tempCandidates = constituency.getCandidates();
        Voter[] tempVoters = constituency.getVoters();
        int[] tempSumWeightsVector1 = new int[tempVoters[0].getTempWeights().length];
        int[] tempSumWeightsVector2 = new int[tempVoters[0].getWeights().length];

        for (int i = 0; i < tempVoters.length; i++) {
            if (tempVoters[i].getTempWeights().length != 1) {
                tempVoters[i].tryActivity(this.activities[activityNr]);

                for (int j = 0; j < tempSumWeightsVector1.length; j++) {
                    tempSumWeightsVector1[j] += tempVoters[i].getTempWeights()[j];
                    tempSumWeightsVector2[j] += tempVoters[i].getWeights()[j];

                }
            }
        }


        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < tempCandidates.length; i++) {
            if (tempCandidates[i].getParty().equals(party) == ifParty) {
                sum1 += Universal.calculateSumWeights(tempSumWeightsVector1, tempCandidates[i]);
                sum2 += Universal.calculateSumWeights(tempSumWeightsVector2, tempCandidates[i]);
            }
        }

        return sum1 - sum2;
    }
}
