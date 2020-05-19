package hello;

import org.springframework.stereotype.Component;

@Component
public class Dodatkowe {
//metody do endpointow
    //zamiana stringa na inta oraz obsluga wyjatkow
    public Integer a(String s){
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }return -1;
    }
//rozroznianie rodzaju wpisanego imienia
    public String plec(String imie){
        if(imie.endsWith("a")){//sprawdzanie ostatniej litery wpisanego imienia
            return "kobieta";
        }
        return "mezczyzna";
    }
}
