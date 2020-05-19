package hello;
//klasa dziedziczaca po Greeting rozszerzena o imie
public class WiecejDanych extends Greeting {
    private final String imie;

    public WiecejDanych(long id, String imie, String wiek) {
        super(id, wiek);
        this.imie = imie;
    }

    public String getImie() {
        return imie;
    }
}
