// Klasa reprezentująca obywatela.
public class Citizen {
    protected final String name;        // Imię obywatela.
    protected final String surname;     // Nazwisko obywatela.
    protected final int constituencyNr; // Numer okręgu, w którym znajdujesię obywatel.

    // Getter imienia obywatela.
    public String getName() {
        return name;
    }

    // Getter nazwiska obywatela.
    public String getSurname() {
        return surname;
    }

    // Konstruktor.
    public Citizen(String name, String surname, int constituencyNr) {
        this.name = name;
        this.surname = surname;
        this.constituencyNr = constituencyNr;
    }
}
