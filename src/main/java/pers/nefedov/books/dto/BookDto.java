package pers.nefedov.books.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    @Nullable
    private Long id;

    @NotEmpty(message = "Код поставщика обязателен")
    @NotBlank
    @Size(min = 1, max = 20)
    private String vendorCode;

    @NotEmpty(message = "Название обязательно")
    @NotBlank
    @Size(min = 1, max = 255)
    private String title;

    @Min(1000)
    @Max(2050)
    private Integer year;

    @NotEmpty(message = "Указание бренда обязательно")
    @NotBlank
    @Size(min = 1, max = 255)
    private String brand;

    @Positive
    private Integer stock;

    @Positive
    private Double price;
}
