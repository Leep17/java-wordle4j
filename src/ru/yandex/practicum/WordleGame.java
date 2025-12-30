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

    private int maxSteps;

    private boolean isFinished;

    public WordleGame(WordleDictionary dictionary) {
        this.steps = 1;
        this.isFinished = false;
        this.dictionary = dictionary;
        this.maxSteps = 7;
        this.answer = dictionary.giveWord().toLowerCase();
    }

    public boolean checkAnswer(String guess, PrintWriter logWriter) throws GameException {

        if ((guess.trim().length() != answer.length() && !guess.isBlank()) || guess.isEmpty()) {
            logWriter.println("Некорректная длина слова\n");
            throw new GameException("Некорректная длина слова\n");
        }
        if (!dictionary.containsWord(guess.trim()) && !guess.isBlank()) {
            logWriter.println("Слово не из словаря\n");
            throw new GameException("Слово не из словаря\n");
        }
        steps++;
        if (guess.trim().equalsIgnoreCase(answer)) {
            isFinished = true;
        }

        if (steps >= maxSteps) {
            logWriter.println("Вы истратили все попытки\n");
            throw new GameException("Вы истратили все попытки!\n");
        }
        return isFinished;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getSteps() {
        return steps;
    }

    public String getAnswer() {
        return answer;
    }

    public String hintsAnswer(String guess) {
        StringBuilder sb = new StringBuilder();

        if (guess.trim().length() == answer.length() && !guess.isBlank()) {
            guess = guess.trim();
        }

        for (int c = 0; c < guess.length(); c++) {
            if (answer.charAt(c) == guess.charAt(c)) {
                sb.append("+");
            } else if (answer.contains(String.valueOf(guess.charAt(c)))) {
                sb.append("^");
            } else {
                sb.append("-");
            }
        }
        return sb.toString();
    }

}
