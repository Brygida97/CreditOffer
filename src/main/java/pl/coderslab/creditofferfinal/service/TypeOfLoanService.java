package pl.coderslab.creditofferfinal.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.TypeOfLoanDTO;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;
import pl.coderslab.creditofferfinal.exception.TypeOfLoanNotFoundException;
import pl.coderslab.creditofferfinal.mapper.TypeOfLoanMapper;
import pl.coderslab.creditofferfinal.repository.TypeOfLoanRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeOfLoanService {

    private final TypeOfLoanRepository typeOfLoanRepository;

    private final TypeOfLoanMapper typeOfLoanMapper;

    public List<TypeOfLoanDTO> getAllTypeOfLoans() {
        List<TypeOfLoan> typeOfLoan = typeOfLoanRepository.findAll();
        return typeOfLoanMapper.toDtoList(typeOfLoan);
    }

    public TypeOfLoanDTO getTypeOfLoanById(Long id) {
        Optional<TypeOfLoan> optionalTypeOfLoan = typeOfLoanRepository.findById(id);
        if (optionalTypeOfLoan.isPresent()) {
            TypeOfLoan typeOfLoan = optionalTypeOfLoan.get();
            return typeOfLoanMapper.toDto(typeOfLoan);
        }
        throw new TypeOfLoanNotFoundException("Typ kredytu o podanym ID nie istnieje");
    }

    public TypeOfLoanDTO createTypeOfLoan(TypeOfLoanDTO typeOfLoanDTO) {
        TypeOfLoan typeOfLoan = typeOfLoanMapper.toEntity(typeOfLoanDTO);
        TypeOfLoan createTypeOfLoan = typeOfLoanRepository.save(typeOfLoan);
        return typeOfLoanMapper.toDto(createTypeOfLoan);
    }

    public TypeOfLoanDTO updateTypeOfLoan(Long id, TypeOfLoanDTO typeOfLoanDTO) {
        Optional<TypeOfLoan> optionalTypeOfLoan = typeOfLoanRepository.findById(id);
        if (optionalTypeOfLoan.isPresent()) {
            TypeOfLoan typeOfLoan = optionalTypeOfLoan.get();
            typeOfLoan.setName_Type(typeOfLoanDTO.getName_Type());
            TypeOfLoan updateTypeOfLoan = typeOfLoanRepository.save(typeOfLoan);
            return typeOfLoanMapper.toDto(updateTypeOfLoan);
        }
        throw new TypeOfLoanNotFoundException("Typ kredytu o podanym ID nie istnieje");
    }

    public TypeOfLoanDTO deleteTypeOfLoan(Long id) {
        Optional<TypeOfLoan> optionalTypeOfLoan = typeOfLoanRepository.findById(id);
        if (optionalTypeOfLoan.isPresent()) {
            TypeOfLoan typeOfLoan = optionalTypeOfLoan.get();
            typeOfLoanRepository.deleteById(id);
            return typeOfLoanMapper.toDto(typeOfLoan);
        }
        throw new TypeOfLoanNotFoundException("Typ kredytu o podanym ID nie istnieje");
    }
}
