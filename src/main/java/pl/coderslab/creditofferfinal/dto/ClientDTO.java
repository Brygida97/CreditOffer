package pl.coderslab.creditofferfinal.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String imie;
    private String nazwisko;
    private String email;
}
