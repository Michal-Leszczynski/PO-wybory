// Klasa abstrakcyjna reprezentująca wyborcę.
// Rozszerza obywatela.
public abstract class Voter extends Citizen {
    protected final int type;           // Typ wyborce (określa preferencje wyborcy podczas głosowania).
    protected String voteName;    // Imię kandydata, na którego wyborca oddał głos.
    protected String voteSurname; // Nazwisko kandydata, na którego wyborca oddał głos.

    // Metoda zwracająca tablicę wag przypisywanych danym cechom przez wyborcę.
    // Będzie ona zwracała prawdziwe wagi jedynie dla wyborców, którzy je posiadają (typ 5 i 8).
    public int[] getWeights() {
        return new int[1];
    }

    // Metoda zwracająca tablicę tymczasowych wag, reprezentującą wagi po próbie zastosowania na
    // nich pewnego działania wyborczego. Będzie ona zwracała prawdziwe wagi jedynie dla wyborców,
    // którzy je posiadają (typ 5 i 8).
    // Można ją traktować jako tablicę reprezentującą sondaż przeprowadzony przez partię
    // zlecającą zadane działanie wyborcze.
    public int[] getTempWeights() {
        return new int[1];
    }

    // Konstruktor.
    public Voter(String name, String surname, int constituencyNr, int type) {
        super(name, surname, constituencyNr);
        this.type = type;
        this.voteName = "";
        this.voteSurname = "";
    }

    // Metoda, która wczytuje parametry wyborcy oraz zwraca obiekt go reprezentujący.
    public static Voter readVoter(Reading reading, int attributesAmount) {
        String name = reading.readString();
        String surname = reading.readString();
        int constituencyNr = reading.readInt();
        int type = reading.readInt();

        if (type == 1) {
            return IronElectorateParty.readIronElectorateParty(reading, name, surname, constituencyNr, type);
        }
        else if (type == 2) {
            return IronElectorateCandidate.readIronElectorateCandidate(reading, name, surname, constituencyNr, type);
        }
        else if (type == 3) {
            return MinimizingOneAttributeAllParties.readMinimizingOneAttributeAllParties(reading, name, surname, constituencyNr, type);
        }
        else if (type == 4) {
            return MaximizingOneAttributeAllParties.readMinimizingOneAttributeAllParties(reading, name, surname, constituencyNr, type);
        }
        else if (type == 5) {
            return  UniversalAllParties.readUniversalAllParties(reading, name, surname, constituencyNr, type, attributesAmount);
        }
        else if (type == 6) {
            return MinimizingOneAttributeOneParty.readMinimizingOneAttributeOneParty(reading, name, surname, constituencyNr, type);
        }
        else if (type == 7) {
            return MaximizingOneAttributeOneParty.readMaximizingOneAttributeOneParty(reading, name, surname, constituencyNr, type);
        }
        else {
            return UniversalOneParty.readUniversalOneParty(reading, name, surname, constituencyNr, type, attributesAmount);
        }
    }

    // Metoda, która ustawia tablicę wag tymczasowych tak,
    // aby reprezentowała ona tablicę wag po dokonaniu pewnego działąnia wyborczego.
    // Prawdziwie wykonuje się jedynie dla wyborców, którzy posiadają wagi (typ 5 i 8).
    public void tryActivity(int[] activity) {}

    // Metoda, która ustawia tablicę wag wyborcy na jego tablicę wag tymczasowych.
    // Prawdziwie wykonuje się jedynie dla wyborców, którzy posiadają wagi (typ 5 i 8).
    public void setActivity() {}

    // Abstrakcyjna metoda, która dla zadanej tablicy kandydatów wybiera z niej
    // tego, na którego zagłosowałby wyborca.
    public abstract Candidate chooseCandidate(Candidate[] candidates);

    // Metoda, która wybiera odpowiedzniego kandydata z zadanej tablicy
    // kandydatów za pomocą metody chooseCandidate() oraz symuluje oddanie
    // na niego głosy przez wyborcę.
    public void vote(Candidate[] candidates) {
        Candidate choice = chooseCandidate(candidates);
        choice.voted();
        this.voteName = choice.getName();
        this.voteSurname = choice.getSurname();
    }

    // Metoda, która wypisuje imię i nazwisko wyborccy oraz kandydata, na którego głosował.
    public void writeVoter() {
        System.out.println("Wyborca: " + this.name + " " + this.surname + " Głosował na: " + this.voteName + " " + this.voteSurname);
    }
}
