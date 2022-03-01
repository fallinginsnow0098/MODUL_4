package backend.controller;

import backend.model.Human;
import backend.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/human")
public class HumanController {
    @Autowired
    IHumanService humanService;

    @GetMapping("")
    public ModelAndView showHumans(@PageableDefault(value = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Human> humans = humanService.findAll(pageable);
        if (humans.isEmpty()) {
            modelAndView.addObject("message", "NO HUMANS");
        }
        modelAndView.addObject("humans", humans);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Optional<Human> human = humanService.findById(id);
        human.ifPresent(human1 -> modelAndView.addObject("human", human1));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("index");
        humanService.delete(id);
        modelAndView.addObject("message", "Delete OK");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("human", new Human());
        return modelAndView;
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("human") Human human, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:http://localhost:8080/human";
        }
        humanService.save(human);
        return "redirect:/human";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Optional<Human> human = humanService.findById(id);
        human.ifPresent(value -> modelAndView.addObject("human", value));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") long id, Model model, @PageableDefault(value = 1) Pageable pageable,
                              @Valid @ModelAttribute("human") Human human, BindingResult bindingResult) {
        human.setId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("human", human);
            return "edit";
        }
        humanService.save(human);
        return "redirect:/human";
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search") String search, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Human> humans = humanService.findByName(pageable, search);
        modelAndView.addObject("humans", humans);
        return modelAndView;
    }
    @GetMapping("/sort")
    public ModelAndView sort(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Human> humans = humanService.findAll(pageable);
        modelAndView.addObject("humans", humans);
        return modelAndView;
    }

    @GetMapping("/asc")
    public ModelAndView sortByAsc(@SortDefault(sort = "name", direction = Sort.Direction.ASC)@PageableDefault Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Human> humans = humanService.findAll(pageable);
        modelAndView.addObject("humans", humans);
        return  modelAndView;
    }

    @GetMapping("/desc")
    public ModelAndView sortByDesc(@SortDefault(sort = "name", direction = Sort.Direction.DESC)@PageableDefault Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Human> humans = humanService.findAll(pageable);
        modelAndView.addObject("humans", humans);
        return  modelAndView;
    }
}
