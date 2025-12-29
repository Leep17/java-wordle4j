package ru.yandex.practicum;

import java.io.PrintWriter;

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

    private static final int maxSteps = 7;

    private boolean isFinished;

    public WordleGame(String answer, WordleDictionary dictionary) {
        this.steps = 1;
        this.isFinished = false;
        this.answer = answer;
        this.dictionary = dictionary;
    }

    public boolean checkAnswer(String guess, PrintWriter logWriter) throws GameException {
        if (guess.length() != answer.length() || guess == null) {
            logWriter.println("Некорректная длина слова\n");
            throw new GameException("Некорректная длина слова\n");
        }
        if (!dictionary.containsWord(guess)) {
            logWriter.println("Слово не из словаря\n");
            throw new GameException("Слово не из словаря\n");
        }
        steps++;
        if (guess.equals(answer)) {
            isFinished = true;
        }

        if (steps >= maxSteps) {
            logWriter.println("Вы истратили все попытки\n");
            throw new GameException("Вы истратили все попытки!\n");
        }
        return isFinished();
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getSteps() {
        return steps;
    }

    public String hintsAnswer(String guess) {
        StringBuilder result = new StringBuilder();

        boolean[] answerUsed = new boolean[answer.length()];

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                result.append('+');
                answerUsed[i] = true;
            } else {
                result.append(' ');
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (result.charAt(i) == '+') {
                continue;
            }
            char letter = guess.charAt(i);
            boolean found = false;
            for (int j = 0; j < answer.length(); j++) {
                if (!answerUsed[j] && letter == answer.charAt(j)) {
                    found = true;
                    answerUsed[j] = true;
                    break;
                }
            }
            if (found) {
                result.setCharAt(i, '^');
            } else {
                result.setCharAt(i, '-');
            }
        }

        return result.toString();
    }

}
