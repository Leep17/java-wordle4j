package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    @Test
    void testLoadDictionary() {
        String logFilename = "log.txt";
        try (PrintWriter logWriter = new PrintWriter(logFilename)) {
            WordleDictionary newDictionary = WordleDictionaryLoader.dictionaryLoader(logWriter);
            Assertions.assertEquals(4165, newDictionary.getDictionarySize());
        } catch (IOException e) {
            System.out.println("Ошибка работы: " + e.getMessage());
        }
    }

    @Test
    void testGetQuestionWord() {
        String logFilename = "log.txt";
        try (PrintWriter logWriter = new PrintWriter(logFilename)) {
            WordleDictionary newDictionary = WordleDictionaryLoader.dictionaryLoader(logWriter);
            Assertions.assertNotEquals(null, newDictionary.giveWord());
        } catch (IOException e) {
            System.out.println("Ошибка работы: " + e.getMessage());
        }
    }

    @Test
    void testLenghtQuestionWord() {
        String logFilename = "log.txt";
        try (PrintWriter logWriter = new PrintWriter(logFilename)) {
            WordleDictionary newDictionary = WordleDictionaryLoader.dictionaryLoader(logWriter);
            Assertions.assertEquals(5, newDictionary.giveWord().length());
        } catch (IOException e) {
            System.out.println("Ошибка работы: " + e.getMessage());
        }
    }
}
