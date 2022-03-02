package backend.controller;

import backend.model.Category;
import backend.model.Song;
import backend.service.ICategoryService;
import backend.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ISongService songService;
    @Autowired
    private ICategoryService categoryService;

    @Value("${file-upload}")
    private String fileUpload;

    @Value("${view}")
    private String view;

    @GetMapping
    public ModelAndView showAllSongs(@PageableDefault(value = 15) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Song> songs = songService.findAll(pageable);
        modelAndView.addObject("songs", songs);
        modelAndView.addObject("view", view);
        return modelAndView;
    }

    @GetMapping("/create-song")
    public ModelAndView showCreateSong() {
        ModelAndView modelAndView = new ModelAndView("create");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("song", new Song());
        return modelAndView;
    }

    @PostMapping("/create-new-song")
    public ModelAndView saveSong(Song song, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        MultipartFile multipartFile = song.getFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(song.getFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        song.setFileURL(fileName);
        songService.save(song);
        return modelAndView;
    }

    @GetMapping("/deltete/{id}")
    public ModelAndView delete(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        Optional<Song> song = songService.findById(id);
        song.ifPresent(value -> songService.delete(value));
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Optional<Song> song = songService.findById(id);
        song.ifPresent(value -> modelAndView.addObject("song", value));
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("view", view);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public ModelAndView editSong(@PathVariable("id")long id, @ModelAttribute("song")Song song, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("index");
        song.setId(id);
        songService.save(song);
        modelAndView.addObject("songs", songService.findAll(pageable));
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") String search, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Song> songs = songService.findByName(pageable, search);
        modelAndView.addObject("songs", songs);
        modelAndView.addObject("view", view);
        return modelAndView;
    }
}
