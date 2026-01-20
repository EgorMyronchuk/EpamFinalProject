package com.epam.rd.autocode.spring.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends User {

    private String phone;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;

}
