package pl.java.scalatech.domain;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.annotations.AmountFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class User {

    private Long id;
    private String name;
    private String login;
    @AmountFormat
    private Long salary;

}
