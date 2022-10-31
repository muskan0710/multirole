package com.example.multirole.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @Column(name = "roleid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String name) {
        this.roleName = name;
    }
}
