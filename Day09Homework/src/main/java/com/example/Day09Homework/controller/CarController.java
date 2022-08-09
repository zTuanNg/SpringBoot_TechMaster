package com.example.Day09Homework.controller;

import com.example.Day09Homework.model.Car;
import com.example.Day09Homework.service.CarService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService service;

//    @GetMapping
//    public String getAll(Model model, String keyword){
//
//        if(keyword == null || keyword == ""){
//            System.out.println("=>>>>keyword :  null");
//        }else{
//            System.out.println("=>>>>keyword : "+keyword);
//        }
//        if(keyword == null){
//            List<Car> cars = service.getAll();
//            model.addAttribute("cars",cars);
//
//        }else{
//            List<Car> cars = service.findCar(keyword);
//            model.addAttribute("cars",cars);
//        }
//
//        return "index";
//    }


    @GetMapping
    public String getAll(Model model, @Param("p1") String kw1, @Param("p2") String kw2){

        System.out.println("===> keyword: " + kw1);
        System.out.println("===> kw2: " + kw2);

      model.addAttribute("cars", service.getAll());

        return "index";
    }

//    @GetMapping("/test")
//    @ResponseBody
//    public String test(@PathVariable(name= "keyword", required = false) String keyword){
//
//        if(keyword == null){
//            return "null";
//        }
//        System.out.println("----> Keyword: " + keyword);
//        List<Car> cars = service.findCar(keyword);
//        if(cars.size() >0){
//            System.out.println(cars);
//        }else{
//            System.out.println("Can not find");
//        }
//        return "ok!";
//    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        service.delete(id);
        return "redirect:/";

    }

    @GetMapping("/new")
    public String create(Model model){

        Car car = new Car();
        model.addAttribute("car",car);

        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(name ="car") Car car,
                       @RequestParam("fileImage")MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("========== fileName: " + fileName);
        car.setImage(fileName);
        Car savedCar = service.save(car);

        String upLoadDir = "./car-photos/" + savedCar.getId();

        Path uploadPath = Paths.get(upLoadDir);

        System.out.println("------uploadPath" + uploadPath.toFile().getAbsolutePath());

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }


        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);

            System.out.println("-----------filePath Dir : "+filePath.toFile().getAbsolutePath());

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Could not save upload file: " + fileName);
        }


        return "redirect:/";

    }


//    @PostMapping("/save")
//    public String save(@ModelAttribute(name ="car") Car car,
//                       @RequestParam("fileImage")MultipartFile multipartFile) throws IOException {
//
//
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        System.out.println("------fileName: " + fileName);
//        car.setImage(fileName);
//        Car savedCar = service.save(car);
//
//        return "redirect:/";
//
//    }

    @GetMapping("edit/{id}")
    public String editCar(@PathVariable("id") Integer id,
                          Model model,
                          RedirectAttributes redirectAttributes){

        Car car = service.getById(id);
        model.addAttribute("car",car);

        return "create";

    }

    @GetMapping("/sort")
    public String sort(Model model){

        List<Car> cars = service.sortByModel();
        model.addAttribute("cars",cars);
        return "index";
    }
}
