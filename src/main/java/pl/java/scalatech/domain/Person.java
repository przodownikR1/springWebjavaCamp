package pl.java.scalatech.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.annotations.AmountFormat;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;
    private String name;
    private String login;
    @AmountFormat
    private Long salary;
    @DateTimeFormat(style = "S-")
    private LocalDate birthDate;

}
