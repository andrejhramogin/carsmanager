package org.example.utils;

import org.example.model.Car;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonService {

    //запись с помощью JSON в файл filename.json
    public static void jsonWriteCarList(List<Car> list, String fileName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileName + ".json"), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //чтение из файла filename.json List<Car>
    //В классе Car должен быть создан пустой конструктор.
    public static List<Car> jsonReadCarList(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName + ".json"), new TypeReference<List<Car>>() {
        });
    }
}
