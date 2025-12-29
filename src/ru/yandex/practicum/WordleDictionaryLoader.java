package ru.yandex.practicum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {
    public static WordleDictionary dictionaryLoader(PrintWriter logWriter) {
        List<String> newList = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader("words_ru.txt"))){
            String line;
            while ((line = fileReader.readLine()) != null) {
                if(line.length()==5) {
                    newList.add(line.replace("ё", "е"));
                }
            }
            logWriter.println("Словарь загружен\n");
            logWriter.println("Кол-во загруженных слов: " + newList.size() + "\n");
        }catch (IOException e){
            logWriter.println("Ошибка при загрузке словаря: " + e.getMessage() + "\n");
        }
        return new WordleDictionary(newList);
    }
}
