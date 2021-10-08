// Klasa reprezentująca partię działającą zachłannie.
// Rozszerza klasę partia oraz korzysta z interfejsu GreedyActivities.
public class GreedyParty extends Party implements GreedyActivities {

    // Konstruktor.
    public GreedyParty(String name, int budget, String type) {
        super(name, budget, type);
    }

    // Metoda, która zwraca numer okręgu oraz działania, jakie partia będzie miała wykonać.
    // Dokonuje wyboru maksumalizując sumę ważoną kandydatów należących do jej partii.
    @Override
    public int[] chooseAct(Constituency[] constituencies, ElectoralActivities acts) {
        return GreedyActivities.chooseAct(constituencies, acts, this.name, 1, true, this.budget);
    }
}
