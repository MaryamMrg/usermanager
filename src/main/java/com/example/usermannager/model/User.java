package com.example.usermannager.model;

public class User {
    private String name;
    private String email;
    private String phone;
    private String post;
    private double salaire;

    public User(String name, String email, String phone, String post, double salaire) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.post = post;
        this.salaire = salaire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
}
