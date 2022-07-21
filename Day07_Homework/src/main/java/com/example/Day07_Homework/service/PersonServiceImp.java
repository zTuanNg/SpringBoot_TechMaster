package com.example.Day07_Homework.service;

import com.example.Day07_Homework.model.Person;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

@Service
public class PersonServiceImp implements PersonService{

    private List<Person> data= new ArrayList<>();

    public PersonServiceImp(){
        this.getData("person.csv");
    }

    @Override
    public void getData(String csv) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csv);
            CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(','); // Dòng đầu tiên sử dụng làm Header
            ObjectReader oReader = mapper.readerFor(Person.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
            Reader reader = new FileReader(file);
            MappingIterator<Person> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
            while (mi.hasNext()) {
                Person person = mi.next();
                data.add(person);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
//        return data;
    }

    @Override
    public List<Person> getAll() {
        return data;
    }

    @Override
    public List<Person> sortPersonByName() {

        return data.stream().sorted(Comparator.comparing(Person::getFullName)).collect(Collectors.toList());
    }

    @Override
    public List<Person> sortPersonByJob() {
        return data.stream().sorted(Comparator.comparing(Person::getJob)).collect(Collectors.toList());
    }

    @Override
    public List<Person> sortPersonByCity() {
        return data.stream().sorted(Comparator.comparing(Person::getCity)).collect(Collectors.toList());
    }

    @Override
    public Map<Integer,Set<String>> findTop5Job() {
        Map<String,Integer> map = new HashMap<>();
        Map<String, Integer> finalMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Map<Integer,Set<String>> result = new HashMap<>();
        data.forEach(p-> finalMap.put(p.getJob(), finalMap.getOrDefault(p.getJob(),0)+1));

       map =  finalMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


       for(int i = 0 ; i < map.values().toArray().length;i++){
           if(set.size() == 5){
               break;
           }
           set.add((Integer) map.values().toArray()[i]);
       }

       for(Integer i : set){
           Set<String> s = map.entrySet().stream()
                   .filter(entry-> Objects.equals(i, entry.getValue()))
                   .map(Map.Entry::getKey).collect(Collectors.toSet());
           result.put(i,s);
       }

       return result;
    }

    @Override
    // top 5 cities have most people
    public Map<Integer, Set<String>> findTop5City() {
        Map<String,Integer> map = new HashMap<>();
        Map<String, Integer> finalMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Map<Integer,Set<String>> result = new HashMap<>();
        data.forEach(p-> finalMap.put(p.getCity(), finalMap.getOrDefault(p.getCity(),0)+1));

        map =  finalMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        for(int i = 0 ; i < map.values().toArray().length;i++){
            if(set.size() == 5){
                break;
            }
            set.add((Integer) map.values().toArray()[i]);
        }

        for(Integer i : set){
            Set<String> s = finalMap.entrySet().stream()
                    .filter(entry-> Objects.equals(i, entry.getValue()))
                    .map(Map.Entry::getKey).collect(Collectors.toSet());
            result.put(i,s);
        }
        return result.entrySet().stream().sorted(comparingByKey()) .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));


    }

    @Override
    public Map<String, Set<String>> findTopJobCity() {

        Map<String,Set<String>> result = new HashMap<>();
        Set<String> cities = new HashSet<>();

        // get all distinct cities
        data.forEach(p->cities.add(p.getCity()));

        for(String city : cities){
            Map<String,Integer> map = new HashMap<>();
            Map<String,Integer> finalMap = new HashMap<>();
            data.stream().filter(p -> p.getCity().equals(city)).toList()
                    .forEach(p-> map.put(p.getJob(), map.getOrDefault(p.getJob(),0)+1));

             finalMap = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));



            // find cities on top
            int top1 = (int) finalMap.values().toArray()[0];
            Set<String> listCitiesTop = map.entrySet().stream()
                    .filter(entry-> Objects.equals(top1, entry.getValue()))
                    .map(Map.Entry::getKey).collect(Collectors.toSet());


            result.put(city,listCitiesTop);

        }

        return result;
    }

    @Override
    public Map<String, Set<String>> findTop5JobByCity() {

        Map<String, Set<String>> result = new HashMap<>();

        Set<String> cities = new HashSet<>();

        // get all distinct cities
        data.forEach(p -> cities.add(p.getCity()));

        for (String city : cities) {
            Set<Integer> set = new HashSet<>();
            Map<String, Integer> map = new HashMap<>();
            Map<String, Integer> finalMap = new HashMap<>();
            data.stream().filter(p -> p.getCity().equals(city)).toList()
                    .forEach(p -> map.put(p.getJob(), map.getOrDefault(p.getJob(), 0) + 1));

            finalMap = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


            // get top5 numbers of job in city
            for(int i = 0 ; i < finalMap.values().toArray().length;i++){
                if(set.size() == 2){
                    break;
                }
                set.add((Integer) finalMap.values().toArray()[i]);
            }

            Set<String> totalS= new HashSet<>();
            for(Integer i : set){
                Set<String> s = finalMap.entrySet().stream()
                        .filter(entry-> Objects.equals(i, entry.getValue()))
                        .map(Map.Entry::getKey).collect(Collectors.toSet());

                totalS.addAll(s);
            }
            result.put(city,totalS);


        }
        return result;
    }


}
