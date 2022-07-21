package com.example.Day08.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.example.Day08.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpServiceImp implements EmpService {

    private List<Employee> data = new ArrayList<>();

    public EmpServiceImp(){
        this.readData("MOCK_DATA.csv");
    }


    @Override
    public void readData(String csv) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csv);
            CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(','); // Dòng đầu tiên sử dụng làm Header
            ObjectReader oReader = mapper.readerFor(Employee.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
            Reader reader = new FileReader(file);
            MappingIterator<Employee> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
            while (mi.hasNext()) {
                Employee person = mi.next();
                data.add(person);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @Override
    public List<Employee> getAll() {
        return data;
    }

    @Override
    public Employee findEmpById(int id) {
        return data.stream().filter(e->e.getId() == id).findAny().get();
    }

    @Override
    public void deleteEmpById(int id) {
        Employee e = findEmpById(id);
        data.remove(e);
    }
}
