// Klasa reprezentująca partię działającą skromnie.
// Rozszerza klasę partia oraz korzysta z interfejsu CostActivities.
public class CheapParty extends Party implements CostActivities {

    // Konstruktor.
    public CheapParty(String name, int budget, String type) { super(name, budget, type); }

    // Metoda, która zwraca numer okręgu oraz działania, jakie partia będzie miała wykonać.
    // Dokonuje wyboru minimalizując jego koszt.
    @Override
    public int[] chooseAct(Constituency[] constituencies, ElectoralActivities acts) {
        if (this.lastActivity[0] != -1) {
            if (this.budget >= acts.ActivityCost(this.lastActivity[1], constituencies[this.lastActivity[0]])) {
                return this.lastActivity;
            }
            else {
                int[] temp = new int[2];
                temp[0] = -1;

                return temp;
            }
        }
        else {
            return CostActivities.chooseAct(constituencies, acts, -1, this.budget);
        }
    }
}
