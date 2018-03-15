package Filtering;

import logic.Event;

public class ContainsWord implements Criterion {

    String word;

    public ContainsWord(String word) {

        this.word = word;

    }

    @Override
    public boolean complies(Event event) {
        return event.toString().contains(word);
    }
}
