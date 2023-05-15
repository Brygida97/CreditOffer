package pl.coderslab.creditofferfinal.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeOfLoanDTO {
        private Long id;
        private String name_Type;
}
