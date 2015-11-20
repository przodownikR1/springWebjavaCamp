package pl.java.scalatech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.annotations.AmountFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String login;
    @AmountFormat
    private Long salary;

}
