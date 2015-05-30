package com.example.database1.database1;

/**
 * Created by ARA on 28-May-15.
 */
public class User {
    public long getId() {
        return id;
    }

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;

    }

    private long id;
    private String email;
    private String name;

    public String getEmail() {
        return email;
    }
}
