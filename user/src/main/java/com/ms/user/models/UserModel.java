package com.ms.user.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
@Data
@NoArgsConstructor
public class UserModel implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String name;
    private String email;
}
