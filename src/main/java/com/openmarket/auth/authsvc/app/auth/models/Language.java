package com.openmarket.auth.authsvc.app.auth.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "language", schema = "omrtprod")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Language entity")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Language ID", example = "1")
    private Integer id;

    @Column(length = 50)
    @Schema(description = "Language name", example = "English")
    private String name;

    @Column(length = 2)
    @Schema(description = "Language code", example = "en")
    private String code;
}