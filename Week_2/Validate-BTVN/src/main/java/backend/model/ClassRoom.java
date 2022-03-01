package backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String className;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
    private List<Student> students;

    public ClassRoom() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
