package ru.yandex.practicum;

import java.io.PrintWriter;
import java.util.LinkedHashSet;
/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    private String answer;

    private int steps;

    private WordleDictionary dictionary;

    private int maxSteps;

    private boolean isFinished;

    LinkedHashSet<Character> guessAnswer = new LinkedHashSet<>();
    LinkedHashSet<String> variants = new LinkedHashSet<>();

    public WordleGame(WordleDictionary dictionary) {
        this.steps = 1;
        this.isFinished = false;
        this.dictionary = dictionary;
        this.maxSteps = 7;
        this.answer = dictionary.giveWord().toLowerCase();
    }

    public boolean checkAnswer(String guess, PrintWriter logWriter) throws GameException {

        if (guess.length() != answer.length() && !guess.isEmpty()) {
            logWriter.println("Некорректная длина слова\n");
            throw new GameException("Некорректная длина слова\n");
        }
        if (!dictionary.containsWord(guess) && !guess.isBlank()) {
            logWriter.println("Слово не из словаря\n");
            throw new GameException("Слово не из словаря\n");
        }
        variants.add(guess);
        if (guess.equalsIgnoreCase(answer)) {
            isFinished = true;
        }

        if (steps >= maxSteps) {
            logWriter.println("Вы истратили все попытки\n");
            throw new GameException("Вы истратили все попытки!\n");
        }
        steps++;
        return isFinished;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getSteps() {
        return steps;
    }

    public String giveHints(PrintWriter logWriter) {

        String bestWord = null;
        int maxCount = -1;

        if (steps == 1) {
            for (String i : dictionary.getWords()) {
                if (i.contains(answer.substring(0,1))) {
                    variants.add(i);
                    logWriter.println("Подсказка: " + bestWord + "\n");
                    return i;
                }
            }
        }
        for (String word : dictionary.getWords()) {
            int count = 0;
            for (char ch : word.toCharArray()) {
                if (guessAnswer.contains(ch)) {
                    count++;
                }
            }
            if (count > maxCount && !variants.contains(word)) {
                maxCount = count;
                bestWord = word;
            }
        }
        if (bestWord != null) {
            variants.add(bestWord);
            logWriter.println("Подсказка: " + bestWord + "\n");
            return bestWord;
        }
        logWriter.println("Подходящих слов нет!\n");
        return "Подходящих слов нет!";
    }

    public String getAnswer() {
        return answer;
    }

    public String hintsAnswer(String guess) {
        StringBuilder sb = new StringBuilder();
        variants.add(guess);
        for (int c = 0; c < guess.length(); c++) {
            if (answer.charAt(c) == guess.charAt(c)) {
                sb.append("+");
                guessAnswer.add(guess.charAt(c));
            } else if (answer.contains(String.valueOf(guess.charAt(c)))) {
                sb.append("^");
                guessAnswer.add(guess.charAt(c));
            } else {
                sb.append("-");
            }
        }
        return sb.toString();
    }

}
