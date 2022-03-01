package backend.controller;

import backend.model.Student;
import backend.service.IClassroomService;
import backend.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class HomeController {
    @Autowired
    IStudentService studentService;
    @Autowired
    IClassroomService classroomService;

    @GetMapping("")
    public ModelAndView showAll(@RequestParam("search")Optional<String> search, @PageableDefault(value = 3)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Student> students;
        if (search.isPresent()){
            students = studentService.findByNameOrPhoneNumber(pageable, search.get());
        } else {
            students = studentService.finAll(pageable);
        }
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create-student")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        modelAndView.addObject("classes", classroomService.findAllClass());
        return modelAndView;
    }
    @GetMapping("/edit-student/{id}")
    public ModelAndView edit(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Optional<Student> student = studentService.findById(id);
        modelAndView.addObject("student", student);
        modelAndView.addObject("classes", classroomService.findAllClass());
        return modelAndView;
    }
    @PostMapping("/save-student")
    public ModelAndView save(@Valid @ModelAttribute Student student, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("create");
        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", "Create failed");
            modelAndView.addObject("color", "Red");
            modelAndView.addObject("student", student);
            return modelAndView;
        }
        modelAndView.addObject("message", "Create success");
        modelAndView.addObject("color", "Blue");
        modelAndView.addObject("student", new Student());
        studentService.save(student);
        modelAndView.addObject("classes", classroomService.findAllClass());
        return modelAndView;
    }
    @PostMapping("/update-student/{id}")
    public ModelAndView update(@ModelAttribute Student student, @PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView("create");
        if (student != null){
            student.setId(id);
            studentService.save(student);
            modelAndView.addObject("message", "Update success");
            modelAndView.addObject("color", "Blue");
        } else {
            modelAndView.addObject("message", "Update failed");
            modelAndView.addObject("color", "Red");
        }
        modelAndView.addObject("student", student);
        modelAndView.addObject("classes", classroomService.findAllClass());
        return modelAndView;
    }
    @GetMapping("/delete-student/{id}")
    public ModelAndView delete(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView("detail");
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()){
            studentService.delete(id);
        }
        return modelAndView;
    }
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView("detail");
        Optional<Student> student = studentService.findById(id);
//        if (student.isPresent()){
//            modelAndView.addObject("student", student.get());
//        }
        student.ifPresent(value -> modelAndView.addObject("student", value));
        return modelAndView;
    }
}
