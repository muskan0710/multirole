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

    @Column(name = "permissionid")
    private String permissionid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid;
    }


}