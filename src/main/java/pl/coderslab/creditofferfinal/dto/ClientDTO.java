package pl.coderslab.creditofferfinal.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String first_Name;
    private String second_Name;
    private String email;
}
