package com.examplet.lab5q1studentgpa.Controller;

import com.examplet.lab5q1studentgpa.ApiResponse.ApiResponse;
import com.examplet.lab5q1studentgpa.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    ArrayList<Student> students = new ArrayList<>();

    @GetMapping("/get-students")
    public ArrayList<Student> getStudents(){
        return students;
    }

    @GetMapping("/get-student-honor/{index}")
    public ApiResponse getStudentHonor(@PathVariable int index){
        if(students.get(index).getGpa() > 3.5){
           return new ApiResponse("Student of index ("+ index+") Achieved First Honor");
        }else if(students.get(index).getGpa() > 3){
            return new ApiResponse("Student of index ("+ index+") Achieved Second Honor");
        }else return new ApiResponse("Student of index ("+ index+") Achieved No Honor");
    }

    @GetMapping("/get-student-above-average")
    public ArrayList<Student> getStudentAboveAverage(){
        ArrayList<Student> aboveAverage = new ArrayList<>();
        double averageGPA = 0;

        for(Student student : students){
            averageGPA += student.getGpa();
        }

        double average = averageGPA / students.size();

        for(Student student : students){
            if(student.getGpa() > average){
                aboveAverage.add(student);
            }
        }
        return aboveAverage;
    }

    @PostMapping("/add-student")
    public ApiResponse addStudent(@RequestBody Student student){
        students.add(student);
        return new ApiResponse("Student added");
    }

    @PutMapping("/update-student/{index}")
    public ApiResponse updateStudent(@RequestBody Student student,@PathVariable int index){
        students.set(index,student);
        return new ApiResponse("Student updated");
    }

    @DeleteMapping("/delete-student/{index}")
    public ApiResponse deleteStudent(@PathVariable int index){
        students.remove(index);
        return new ApiResponse("Student removed");
    }
}
