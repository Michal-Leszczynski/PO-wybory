// Klasa reprezentująca partię działającą z rozmachem.
// Rozszerza klasę partia oraz korzysta z interfejsu CostActivities.
public class ExpensiveParty extends Party implements CostActivities {

    // Konstrukotr.
    public ExpensiveParty(String name, int budget, String type) {
        super(name, budget, type);
    }

    // Metoda, która zwraca numer okręgu oraz działania, jakie partia będzie miała wykonać.
    // Dokonuje wyboru maksymalizując jego koszt.
    @Override
    public int[] chooseAct(Constituency[] constituencies, ElectoralActivities acts) {
        if (this.lastActivity[0] != -1 && this.budget >= acts.ActivityCost(this.lastActivity[1], constituencies[this.lastActivity[0]])) {
            return this.lastActivity;
        }
        else {
            return CostActivities.chooseAct(constituencies, acts, 1, this.budget);
        }
    }
}
