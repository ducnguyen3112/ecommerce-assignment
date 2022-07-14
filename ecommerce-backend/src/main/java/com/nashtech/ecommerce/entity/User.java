package com.nashtech.ecommerce.entity;

import com.nashtech.ecommerce.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "[users]")
public class User {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<Role>();
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Formula("CONCAT_WS( ' ', first_name, last_name ) ")
    private String fullName;
    @Column(name = "phone_num")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "registered_at")
    private LocalDateTime registeredAt;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "address")
    private String address;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "status")
    private UserStatus status;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Order> orderList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> cartList;
}