package pers.nefedov.books.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @NotEmpty
    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @NotBlank
    @NotEmpty
    @Column(name = "title", nullable = false)
    private String title;

    @Min(1000)
    @Max(2050)
    @Column(name = "year", nullable = false, length = 4)
    private int year;

    @Column(name = "brand", nullable = false)
    private String brand;

    @PositiveOrZero
    @Column(name = "stock", nullable = false)
    private int stock;

    @Positive
    @Column(name = "price", nullable = false)
    private Double price;
}
