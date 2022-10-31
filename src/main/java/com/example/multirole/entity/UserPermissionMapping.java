package com.example.multirole.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_permission_mapping")
public class UserPermissionMapping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "username")
    private String permissionid;

}
