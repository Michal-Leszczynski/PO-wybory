// Klasa reprezentująca okrąg wyborczy.
public class Constituency {
    private final int number;             // Numer okręgu.
    private final int votersAmount;       // Ilość wyborców w okręgu.
    private final Candidate[] candidates; // Tablica kandydatów w okręgu.
    private final Voter[] voters;         // Tablica wyborców w okręgu.
    private boolean ifMerged;       // Boolean określający, czy okrąg jest połączony z okręgiem o numerze o jeden mniejszym.

    // Getter tablicy wyborców.
    public Voter[] getVoters() {
        return this.voters;
    }

    // Getter ilości wyborców.
    public int getVotersAmount() {
        return this.votersAmount;
    }

    // Getter ilości kandydatów.
    public int getCandidatesAmount() {
        return this.candidates.length;
    }

    // Getter kandydata przechowywanego w tablic kandydatów pod zadanym indexie.
    public Candidate getCandidateNr(int nr) {
        return this.candidates[nr];
    }

    // Getter tablicy kandydatów.
    public Candidate[] getCandidates() {
        return this.candidates;
    }

    // Getter booleana określającego, czy okrąg jest połączony z okręgiem o numerze o jeden mniejszym.
    public boolean getIfMerged() {
        return  this.ifMerged;
    }

    public Constituency(int number, int amountOfVoters, Candidate[] candidates, Voter[] voters) {
        this.number = number;
        this.votersAmount = amountOfVoters;
        this.candidates = candidates;
        this.voters = voters;
        this.ifMerged = false;
    }

    // Metoda, która ustawia boolean ifMerged na true;
    private void setMerged() { this.ifMerged = true; }

    // Metoda, która włącza do oryginalnego okręgu następny okrąg.
    // Po połączeniu powstały okrąg jest w takiej formie, jakby od początku
    // wszystkie parametry następnego okręgu do niego należały.
    public Constituency mergeConstituences(Constituency next, int partiesAmount) {
        Candidate[] tempCandidates = new Candidate[this.getCandidatesAmount() + next.getCandidatesAmount()];
        Voter[] tempVoters = new Voter[this.getVotersAmount() + next.getVotersAmount()];
        int index = 0;
        int index1 = 0;
        int index2 = 0;

        for (int i = 0; i < partiesAmount; i++) {
            for (int j = 0; j < this.getCandidatesAmount() / partiesAmount; j++) {
                tempCandidates[index] = this.candidates[index1];
                index++;
                index1++;
            }

            for (int j = 0; j < next.getCandidatesAmount() / partiesAmount; j++) {
                tempCandidates[index] = next.getCandidateNr(index2);
                tempCandidates[index].addListNr(this.getCandidatesAmount() / partiesAmount);
                index++;
                index2++;
            }
        }

        for (int i = 0; i < this.getVotersAmount() + next.getVotersAmount(); i++) {
            if (i < this.getVotersAmount()) {
                tempVoters[i] = this.getVoters()[i];
            }
            else {
                tempVoters[i] = next.getVoters()[i - this.getVotersAmount()];
            }
        }

        next.setMerged();

        return new Constituency(this.number, tempVoters.length, tempCandidates, tempVoters);
    }

    // Metoda, która symuluje oddanie głosów przez wyborców w okręgu.
    public void voting() {
        for (int i = 0; i < this.voters.length; i++) {
            this.voters[i].vote(this.candidates);
        }
    }

    // Metoda wypisująca wyborców oraz kandydatów z danego okręgu.
    public void writeConstituency() {
        for (int i = 0; i < this.voters.length; i++) {
            this.voters[i].writeVoter();
        }

        for (int i = 0; i < candidates.length; i++) {
            this.candidates[i].writeCandidate();
        }
    }
}
