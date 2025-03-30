package de.dlsa.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Rollen")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id")
    private Integer role_id;

    @Column(name = "rollenname")
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

}
