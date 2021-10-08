// Klasa reprezentująca partię działającą złośliwie (mój własny pomysł).
// Rozszerza klasę partia oraz korzysta z interfejsu GreedyActivities.
public class MeanParty extends Party implements GreedyActivities {

    // Konstrukotr.
    public MeanParty(String name, int budget, String type) {
        super(name, budget, type);
    }

    // Metoda, która zwraca numer okręgu oraz działania, jakie partia będzie miała wykonać.
    // Dokonuje wyboru minimalizując sumę ważoną kandydatów należących do innych partii.
    @Override
    public int[] chooseAct(Constituency[] constituencies, ElectoralActivities acts) {
        return GreedyActivities.chooseAct(constituencies, acts, this.name, -1, false, this.budget);
    }
}
