package ru.itmentor.spring.boot_security.demo.model;

import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50 characters")
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "profession")
    private String profession;

    @Column(name = "avatar_url")
    @URL(message = "Provide valid avatar URL")
    private String avatarURL;

    @Column(name = "smart")
    private boolean hasBrains = false;

    @Column(name = "age")
    @Min(value = 1, message = "Minimal age is 1")
    private int age;

    public User() {
    }

    public User(Set<Role> roles, String name, String profession, String avatarURL, boolean hasBrains, int age) {
        this.name = name;
        this.roles.addAll(roles);
        this.profession = profession;
        this.hasBrains = hasBrains;
        this.age = age;
        this.avatarURL = avatarURL;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean hasRole(String role) {
        return getAuthorities().stream().anyMatch(authority -> Objects.equals(authority.getAuthority(), role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addAuthorities(Role ...roles) {
        this.roles.addAll(List.of(roles));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public boolean isHasBrains() {
        return hasBrains;
    }

    public void setHasBrains(boolean hasBrains) {
        this.hasBrains = hasBrains;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        String pattern = "Пользователь - '%s', id - %s\nПрофессия - '%s'\nУмный? - %s\nВозраст - %d\nСтеснительный? - %s}";
        return String.format(pattern, name, id, profession, hasBrains ? "yes" : "not much", age, avatarURL == null || avatarURL.isEmpty() ? "yes" : "вряд ли");
    }
}
