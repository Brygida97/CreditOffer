package pl.coderslab.creditofferfinal.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.creditofferfinal.dto.TypeOfLoanDTO;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypeOfLoanMapper {

    public TypeOfLoanDTO toDto(TypeOfLoan typeOfLoan) {
        TypeOfLoanDTO typeOfLoanDTO = new TypeOfLoanDTO();
        typeOfLoanDTO.setId(typeOfLoan.getId());
        typeOfLoanDTO.setName_Type(typeOfLoan.getName_Type());
        return typeOfLoanDTO;
    }

    public TypeOfLoan toEntity(TypeOfLoanDTO typeOfLoanDTO) {
        TypeOfLoan typeOfLoan = new TypeOfLoan();
        typeOfLoan.setId(typeOfLoanDTO.getId());
        typeOfLoan.setName_Type(typeOfLoanDTO.getName_Type());
        return typeOfLoan;
    }

    public List<TypeOfLoanDTO> toDtoList(List<TypeOfLoan> typeOfLoan) {
        return typeOfLoan.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
