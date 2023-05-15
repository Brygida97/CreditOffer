package pl.coderslab.creditofferfinal.mapper;

import pl.coderslab.creditofferfinal.dto.TypeOfLoanDTO;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;

public class TypeOfLoanMapper {

    public static TypeOfLoanDTO toDTO(TypeOfLoan typeOfLoan){
        TypeOfLoanDTO typeOfLoanDTO = new TypeOfLoanDTO();
        typeOfLoanDTO.setId(typeOfLoan.getId());
        typeOfLoanDTO.setName_Type(typeOfLoan.getName_Type());
        return typeOfLoanDTO;
    }

    public static TypeOfLoan toEntity(TypeOfLoanDTO typeOfLoanDTO){
        TypeOfLoan typeOfLoan = new TypeOfLoan();
        typeOfLoan.setId(typeOfLoanDTO.getId());
        typeOfLoan.setName_Type(typeOfLoanDTO.getName_Type());
        return typeOfLoan;
    }
}
