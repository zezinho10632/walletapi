package entity;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String name;
}
