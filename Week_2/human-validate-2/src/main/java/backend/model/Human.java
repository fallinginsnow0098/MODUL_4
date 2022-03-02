package backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name should be without number or special keys")
    private String name;
    @Pattern(regexp = "^0[0-9]{8,10}$", message = "Phone should be start by 0 and 10 or 11 keys long")
    private String phone;
    private String address;
    @Pattern(regexp = "^[0-9]{9}$", message = "ID Card should be 9 keys long")
    private String idCard;

    public Human() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
