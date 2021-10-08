import java.util.ArrayList;

// Klasa reprezentująca kraj (Bajtocję).
public class Country {
    private final Constituency[] constituencies; // Tablica okręgów wyborczych zawartych w państwie.
    private final Party[] parties;               // Tablica partii działających w państwie.
    private final int attributesNr;              // Ilość cech kandydatów.
    private final ElectoralActivities acts;      // Dostępne działania wyborcze.

    // Konstruktor.
    public Country(Constituency[] constituencies, Party[] parties, int attributesNr, ElectoralActivities acts) {
        this.constituencies = constituencies;
        this.parties = parties;
        this.attributesNr = attributesNr;
        this.acts = acts;
    }

    // Metoda, która wczytuje parametry kraju oraz zwraca obiekt go reprezentujący.
    public static Country readCountry(Reading reading) {
        int n = reading.readInt();
        int p = reading.readInt();
        int d = reading.readInt();
        int c = reading.readInt();

        int constituenciesToMergeAmount = reading.readInt();
        int[] constituenciesToMerge = new int[constituenciesToMergeAmount];

        for (int i = 0; i < constituenciesToMergeAmount; i++) {
            constituenciesToMerge[i] = reading.readFirstIntOfPair();
        }

        Party[] parties = Party.readAllParties(reading, p);

        int[] votersAmount = new int[n];

        for (int i = 0; i < n; i++) {
            votersAmount[i] = reading.readInt();
        }

        ArrayList<Candidate[]> allCandidates = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Candidate[] temp = new Candidate[votersAmount[i] / 10 * p];

            for (int j = 0; j < votersAmount[i] / 10 * p; j++) {
                temp[j] = Candidate.readCandidate(reading, c);
            }

            allCandidates.add(temp);
        }

        Constituency[] constituencies = new Constituency[n];

        for (int i = 0; i < n; i ++) {
            Voter[] voters = new Voter[votersAmount[i]];

            for (int j = 0; j < votersAmount[i]; j++) {
                voters[j] = Voter.readVoter(reading, c);
            }

            constituencies[i] = new Constituency(i + 1, votersAmount[i], allCandidates.get(i), voters);
        }

        for (int i = 0; i < constituenciesToMergeAmount; i++) {
            int tempNr = constituenciesToMerge[i] - 1;
            constituencies[tempNr] = constituencies[tempNr].mergeConstituences(constituencies[tempNr + 1], p);
        }

        ElectoralActivities acts = ElectoralActivities.readElectoralActivities(reading, c, d);

        return new Country(constituencies, parties, c, acts);
    }

    // Metoda, dla kolejnych partii przeprowadza ich kampanie wyborcze.
    public void campaign() {
        for (int i = 0; i < this.parties.length; i++) {
            while (this.parties[i].makeAction(this.constituencies, this.acts)) {}
        }
    }

    // Metoda, która przeprowadza głosowanie w całym kraju.
    public void voting() {
        for(int i = 0; i < constituencies.length; i++) {
            if (!constituencies[i].getIfMerged()) {
                constituencies[i].voting();
            }
        }
    }

    // Metoda, która oblicza, ile głosów uzyskały partie w zadanym okręgu.
    // Zwraca tablicę reprezentującą ilości zdobytych głosów przez kolejne partie w okręgu.
    private int[] calculateVotesConstituency(Constituency constituency) {
        int[] result = new int[this.parties.length];
        Candidate[] tempCandidates = constituency.getCandidates();
        int partyNr = -1;
        String prevParty = "";

        for (int i = 0; i < tempCandidates.length; i++) {
            if (!prevParty.equals(tempCandidates[i].getParty())) {
                partyNr++;
                prevParty = tempCandidates[i].getParty();
            }

            result[partyNr] += tempCandidates[i].getVotes();
        }

        return result;
    }

    // Metoda, która wypisuje rezultaty wyborów dla zadanego sposoby
    // przeliczania głosów na mandaty.
    public void results(CountingVotesMethod method) {
        method.writeMethodName();

        int[] mandatesTotal = new int[this.parties.length];

        for (int i = 0; i < this.constituencies.length; i++) {
            if (this.constituencies[i].getIfMerged()) {
                continue;
            }

            System.out.print("Numer okręgu: ");

            if (i + 1 < this.constituencies.length && this.constituencies[i + 1].getIfMerged()) {
                System.out.println("(" + (i + 1) + "," + (i + 2) + ")");
            }
            else {
                System.out.println(i + 1);
            }

            this.constituencies[i].writeConstituency();

            int votes[] = calculateVotesConstituency(this.constituencies[i]);
            int[] mandates = method.calculateMandates(votes, constituencies[i].getVotersAmount() / 10);

               System.out.print("Wyniki w okręgu: ");

            for (int j = 0; j < this.parties.length; j++) {
                   System.out.print("(" + this.parties[j].getName() + ", " + mandates[j] + ") ");

                   mandatesTotal[j] += mandates[j];
            }

            System.out.println();
        }

        System.out.print("Krajowe wyniki: ");

        for (int i = 0; i < this.parties.length; i++) {
            System.out.print("(" + this.parties[i].getName() + ", " + mandatesTotal[i] + ") ");
        }

        System.out.println();
    }
}
