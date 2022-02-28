package backend._Controller;

import backend._Model.Classes;
import backend._Model.Student;
import backend._Service.SClass;
import backend._Service.SStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class MyController {
    @Autowired
    private SStudent studentService;
    @Autowired
    private SClass classService;
    @ModelAttribute(name = "classes")
    private Iterable<Classes> allClass(){
        return classService.findAll();
    }

    @GetMapping
    private ModelAndView showStudent(){
        ModelAndView modelAndView = new ModelAndView("index");
        Iterable<Student> students = studentService.findAll();
        modelAndView.addObject("students", students);
        return modelAndView;
    }
    @GetMapping("{id}")
    private ModelAndView showDetailStudent(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("detail");
        Optional<Student> student = studentService.findById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }
}
