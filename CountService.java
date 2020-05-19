package hello;

import org.springframework.stereotype.Component;

@Component
public class CountService {
//obliczanie dlugosci slowa:
    public int count(String word){
        return word.length();
    }
}