package org.cars.service;

import org.cars.model.Car;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonInOutServiceImpl implements InOutService {

    //запись с помощью JSON в файл filename.json
    @Override
    public void writeCarList(List<Car> list, String fileName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileName + ".json"), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //чтение из файла filename.json List<Car>
    //В классе Car должен быть создан пустой конструктор.
    @Override
    public List<Car> readCarList(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName + ".json"), new TypeReference<List<Car>>() {
        });
    }
}
