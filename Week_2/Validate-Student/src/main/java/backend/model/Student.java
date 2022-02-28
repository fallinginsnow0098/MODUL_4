package backend.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "student")
@Component
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]", message = "Name should not have number")
    private String name;

    @Range(min = 6, max = 50, message = "Age should be between 6 and 50")
    private int age;
    @Pattern(regexp="(^0\\d{10,11}$)", message = "Phone number should be 10 or 11 keys And must be start by 0")
    private String phone;
    private String address;
    @Min(value = 0, message = "PGA should not be less than 0")
    @Max(value = 10, message = "PGA should not be bigger than 10")
    private double pga;
    @ManyToOne
    @JoinColumn(name = "class_name")
    private ClassRoom classRoom;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPga() {
        return pga;
    }

    public void setPga(double pga) {
        this.pga = pga;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
