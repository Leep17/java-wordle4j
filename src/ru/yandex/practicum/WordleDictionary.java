package ru.yandex.practicum;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private List<String> words;
    Random rand = new Random();

    public WordleDictionary(List<String> words) {
        this.words = words;
    }

    public boolean containsWord(String word) {
        return words.contains(word);
    }

    public int getDictionarySize() {
        return words.size();
    }

    public String giveWord() {
        Collections.shuffle(words);
        int index = rand.nextInt(words.size());
        return words.get(index);
    }
}
