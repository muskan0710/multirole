package com.example.multirole.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    @Id
    @Column(name = "permissionid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "permission_area")
    private int permissionArea;   //roleid

    @Column(name = "permission_type")
    private int permissionType;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPermissionArea() {
        return permissionArea;
    }

    public void setPermissionArea(int permissionArea) {
        this.permissionArea = permissionArea;
    }

    public int getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(int permissionType) {
        this.permissionType = permissionType;
    }




}

