package com.openmarket.auth.authsvc.app.auth.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "users", schema = "omrtprod")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "first_name", length = 40)
    private String firstName;

    @Column(name = "last_name", length = 40)
    private String lastName;

    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "email_address", length = 80)
    private String emailAddress;

    @Column(name = "mobile_number", length = 15)
    private String mobileNumber;

    @Column(length = 20)
    private String password;

    @Column(name = "user_type_id")
    private Integer userTypeId;

    @Column(name = "wallet_id")
    private UUID walletId;

    @Column(name = "default_language_id")
    private Integer defaultLanguageId;
}