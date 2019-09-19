package com.restapi.agriculture.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserRole {

    @Id
    @Column(name = "id_user", nullable = false)
    private String idUsers;

    @Column(name = "id_roles", nullable = false)
    private String idRoles;
}
