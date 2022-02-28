package backend.controller;

import backend.model.ClassRoom;
import backend.model.Student;
import backend.service.IClassRoomService;
import backend.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassRoomService classRoomService;
    @GetMapping
    public ModelAndView showAllStudent(@PageableDefault(value = 10)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("student/home");
        Page<Student> students = studentService.findAll(pageable);
        if (students.isEmpty()){
            modelAndView.addObject("message", "Database is Empty !");
        }
        modelAndView.addObject("students", students);
        return modelAndView;
    }
    @GetMapping("/findByName/{name}")
    public ModelAndView findStudentByName(@PathVariable String name, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("student/home");
        Page<Student> students = studentService.findAllByName(name, pageable);
        modelAndView.addObject("students", students);
        return modelAndView;
    }
    @GetMapping("/findByPgaBetween/{minPga}&{maxPga}}")
    public ModelAndView findStudentByPgaBetween(@PathVariable double minPga, double maxPga, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("student/home");
        Page<Student> students = studentService.findAllByPga(minPga, maxPga, pageable);
        modelAndView.addObject("students", students);
        return modelAndView;
    }
    @GetMapping("/findByPhone/{phone}")
    public ModelAndView findStudentByPhone(@PathVariable String phone, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("student/detail");
        Optional<Student> student = studentService.findAllByPhone(phone, pageable);
        modelAndView.addObject("student", student);
        return modelAndView;
    }
    @GetMapping("/classrooms")
    public ModelAndView showAllClassrooms(){
        ModelAndView modelAndView = new ModelAndView("classroom/home");
        Iterable<ClassRoom> classRooms = classRoomService.findAllClassrooms();
        modelAndView.addObject("classroom", classRooms);
        return modelAndView;
    }
    @GetMapping("/create-student")
    public ModelAndView showFormCreateStudent(){
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }
    @GetMapping("create-clsasroom")
    public ModelAndView showFormCreateClsasRoom(){
        ModelAndView modelAndView = new ModelAndView("classroom/create");
        modelAndView.addObject("classroom", new ClassRoom());
        return modelAndView;
    }
    @PostMapping("/save-student")
    public ModelAndView saveStudent(@ModelAttribute Student student){
        ModelAndView modelAndView = new ModelAndView("student/home");
        Student createStudent = studentService.save(student);
        if (createStudent != null){
            Iterable<ClassRoom> classRooms =  classRoomService.findAllClassrooms();
            modelAndView.addObject("classrooms", classRooms);
            modelAndView.addObject("message", "Create success");
        }
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("student/home");
        studentService.delete(id);
        modelAndView.addObject("message", "Delete success");
        return modelAndView;
    }
    @GetMapping("/detail/{id}")
    public ModelAndView studentDetail(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("student/detail");
        Optional<Student> student = studentService.findById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

}
