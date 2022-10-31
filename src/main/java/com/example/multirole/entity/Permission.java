package com.example.multirole.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    @Id
    @Column(name = "permissionid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    @Column(name = "permission_area")
//    private int permissionArea;

    @Column(name = "permission_type")
    private String permissionType;

}

