// Abstrakcyjna klasa reprezentująca partię polityczną.
public abstract class Party {
    protected final String name;     // Nazwa partii.
    protected int budget;            // Budżet partii.
    protected final String type;     // Typ partii (określa rodzaj stosowanej kampanii wyborczej).
    protected int[] lastActivity;    // Tablica dwuelementowa zawierająca kolejno numer okręgu oraz działania, które ostatnio wykonała partia.

    // Getter nazwy partii.
    public String getName() {
        return this.name;
    }

    // Konstruktor.
    public Party(String name, int budget, String type) {
        this.name = name;
        this.budget = budget;
        this.type = type;
        this.lastActivity = new int[2];
        this.lastActivity[0] = -1;
    }

    // Metoda, która wczytuje parametry, tworzy oraz zwraca tablicę obiektów klasy partia.
    public static Party[] readAllParties(Reading reading, int partiesAmount) {
        Party[] parties = new Party[partiesAmount];
        String[] names = new String[partiesAmount];
        int[] budgets = new int[partiesAmount];

        for (int i = 0; i < partiesAmount; i++) {
            names[i] = reading.readString();
        }

        for (int i = 0; i < partiesAmount; i++) {
            budgets[i] = reading.readInt();
        }

        for (int i = 0; i < partiesAmount; i++) {
            String type = reading.readString();

            if (type.equals("R")) {
                parties[i] = new ExpensiveParty(names[i], budgets[i], type);
            }
            else if (type.equals("S")) {
                parties[i] = new CheapParty(names[i], budgets[i], type);
            }
            else if (type.equals("W")) {
                parties[i] = new MeanParty(names[i], budgets[i], type);
            }
            else if (type.equals("Z")) {
                parties[i] = new GreedyParty(names[i], budgets[i], type);
            }
        }

        return parties;
    }

    // Abstrakcyjna metoda, która na podstawie typu partii wybiera
    // numer okręgu oraz działania, jakie partia będzie miała wykonać.
    // Zwraca tablicę intów od długości 2. Pierwsza wartość to numer okręgu,
    // a następna to numer działania. Jeżeli partia nie może wykonać już żadnego
    // działania, to na pierwszej pozycji tablicy znajduje się -1. Partia wybiera
    // tylko w taki sposób, aby stać ją było na przeprowadzenie wybranego działania
    // w wybranym okręgu.
    public abstract int[] chooseAct(Constituency[] constituencies, ElectoralActivities acts);

    // Metoda, która wybiera działanie oraz okrąg, na którym będzie stosowane
    // (za pomocą metody chooseAct()) oraz rzeczywiście stosuje to działania.
    // Zwraca true, jeżeli udało się przeprowadzić działanie oraz false
    // w przeciwnym wypadku.
    boolean makeAction(Constituency[] constituencies, ElectoralActivities acts) {
        int[] result = this.chooseAct(constituencies, acts);
        this.lastActivity = result;

        if (result[0] == -1) {
            return false;
        }
        else {
            int cost = acts.ActivityCost(result[1], constituencies[result[0]]);
            Voter[] tempVoters = constituencies[result[0]].getVoters();

            for (int i = 0; i < tempVoters.length; i++) {
                tempVoters[i].tryActivity(acts.getActivities()[result[1]]);
                tempVoters[i].setActivity();
            }

            this.budget -= cost;

            return true;
        }
    }
}
