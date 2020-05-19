package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class GreetingController {
//templates to wyswietlania komuinikatow
    private static final String template = "Hello, %s!";
    private static final String template2 = "Twoj wiek to: %s!";//do endpointa zwracającego wiek
    private static final String template3 = "Podany wiek jest strasznie wysoki: %d!";
    private static final String template4 = "Podano niepoprawny wiek, czyli %s!";
    private static final String template5 = "Podane imie to: %s,a plec to :%s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired // Wstrzykiwanie zależności przez pole
            CountService countService;
    @Autowired // Wstrzykiwanie zależności przez pole
            Dodatkowe dodatkowe;
//zmiana nazwy endpointa na "bye": (Zadanie1)
//drugi wariant GreetingController zwraca długość wpisanego imienia w JSON'ie
    @RequestMapping(path="/bye", method = GET)    // odpowiada endpoin'tom http
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {//przyjmowanie parametru
        return new Greeting(counter.incrementAndGet(),String.format(template, countService.count(name)));//sluzy do zwracania informacji w JSON'ie
    }

//nowy endpoint utworzony poprzez skopiowanie konstruktora Greeting:
//przerobiony drugi endpoint zwracający wpisane imię w JSON'ie
    @RequestMapping(path="/zadanie2", method = GET)    // odpowiada endpoin'tom http
    public Greeting greeting1(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),String.format(template, name));
    }

//JSON w tym endpoincie zwraca wiek osoby
    @RequestMapping(path="/zadanie3", method = GET)    // odpowiada endpoin'tom http
    public Greeting greeting2(@RequestParam(value = "wiek", defaultValue = "0") String wiek) {
        int b = dodatkowe.a(wiek);
        if(b>119) {
            return new Greeting(counter.incrementAndGet(), String.format(template3, b));
        } else if (b>0){
            return new Greeting(counter.incrementAndGet(), String.format(template2, b));
        } else {
            return new Greeting(counter.incrementAndGet(), String.format(template4, wiek));
        }
    }

    //JSON w tym endpoincie zwraca wiek oraz imie osoby
    @RequestMapping(path="/zadanie4", method = GET)    // odpowiada endpoin'tom http
    public WiecejDanych greeting3(@RequestParam(value = "wiek", defaultValue = "0") String wiek, @RequestParam(value = "imie", defaultValue = "") String imie) {
        int b = dodatkowe.a(wiek);
        if(b>119) {
            return new WiecejDanych(counter.incrementAndGet(),imie, String.format(template3, b));
        } else if (b>0){
            return new WiecejDanych(counter.incrementAndGet(), imie, String.format(template2, b));
        } else {
            return new WiecejDanych(counter.incrementAndGet(),imie, String.format(template4, wiek));
        }
    }

    //JSON w tym endpoincie zwraca wiek ,imie oraz plec osoby
    @RequestMapping(path="/zadanie5", method = GET)    // odpowiada endpoin'tom http
    public WiecejDanych greeting4(@RequestParam(value = "wiek", defaultValue = "0") String wiek, @RequestParam(value = "imie", defaultValue = "") String imie) {
        int b = dodatkowe.a(wiek);
        if(b>119) {
            return new WiecejDanych(counter.incrementAndGet(), String.format(template5, imie, dodatkowe.plec(imie)), String.format(template3, b));
        } else if (b>0){
            return new WiecejDanych(counter.incrementAndGet(), String.format(template5, imie, dodatkowe.plec(imie)), String.format(template2, b));
        } else {
            return new WiecejDanych(counter.incrementAndGet(), String.format(template5, imie, dodatkowe.plec(imie)), String.format(template4, wiek));
        }
    }
//wyszukiwanie podanego słowa:
    @RequestMapping(path="/zadanie6", method = GET)    // odpowiada endpoin'tom http
    public Greeting greeting5(@RequestParam(value = "wyszukaj", defaultValue = "") String wyszukiwaneSlowo) {
        String tab[] = {"kot", "pies", "kolo", "zadanie"};
        for (String s : tab){//for each przeszukuje tablice
            if(s.equals(wyszukiwaneSlowo)){
                return new Greeting(counter.incrementAndGet(),"Znaleziono");
            }
        }
        return new Greeting(counter.incrementAndGet(),"Nie znaleziono");
    }
}
