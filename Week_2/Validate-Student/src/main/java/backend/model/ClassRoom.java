package backend.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classroom")
@Component
public class ClassRoom  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_id;
    private String class_name;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Student> students;

    public ClassRoom() {
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
