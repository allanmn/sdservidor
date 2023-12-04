package com.example.sdservidor.Models;

import javax.persistence.*;

@Entity
@Table(name = "jwt_sessions")
public class JwtSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "token")
    private String token;

    @Column(name = "ip")
    private String ip;

    public JwtSession(User user, String token, String ip) {
        this.user = user;
        this.token = token;
        this.ip = ip;
    }

    public JwtSession() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String jwtToken) {
        this.token = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}