package ru.yandex.practicum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    public static void main(String[] args) {
        String logFilename = "log.txt";
        String hints;
        Scanner scanner = new Scanner(System.in);
        try (PrintWriter logWriter = new PrintWriter(logFilename)) {
            logWriter.println("Игра началась\n");
            WordleDictionary newDictionary = WordleDictionaryLoader.dictionaryLoader(5, logWriter);
            WordleGame game = new WordleGame(newDictionary);
            System.out.println(game.getAnswer());
            logWriter.println("Загаданное слово: " + game.getAnswer() + "\n");
            System.out.print("Игра началась\n");
            System.out.print("У Вас 6 попыток!\n");
            while (!game.isFinished()) {
                System.out.print("Введите слово: ");
                String guess = scanner.nextLine().trim().toLowerCase();
                logWriter.println("Попытка № " + game.getSteps() + "\n");
                logWriter.println("Ответ: " + guess + "\n");
                if (guess.isEmpty()) {
                    guess = game.giveHints(logWriter);
                    System.out.println("Подсказка: " + guess);
                }
                game.checkAnswer(guess, logWriter);
                hints = game.hintsAnswer(guess);
                logWriter.println("Подсказка: " + hints + "\n");
                if (game.isFinished()) {
                    System.out.println("Слово угадано! Победа!");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка работы: " + e.getMessage());
        } catch (GameException e) {
            System.out.println(e.getMessage());

        }
    }
}
