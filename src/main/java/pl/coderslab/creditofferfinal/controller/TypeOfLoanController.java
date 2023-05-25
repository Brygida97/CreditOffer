package pl.coderslab.creditofferfinal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.creditofferfinal.dto.TypeOfLoanDTO;
import pl.coderslab.creditofferfinal.exception.TypeOfLoanNotFoundException;
import pl.coderslab.creditofferfinal.service.TypeOfLoanService;

import java.util.List;

@RestController
@RequestMapping("/types")
@AllArgsConstructor
public class TypeOfLoanController {

    private TypeOfLoanService typeOfLoanService;

    @GetMapping
    public List<TypeOfLoanDTO> getAllTypeOfLoans() {
        List<TypeOfLoanDTO> types = typeOfLoanService.getAllTypeOfLoans();
        return types;
    }

    @GetMapping("/{id}")
    public TypeOfLoanDTO getTypeOfLoanById(@PathVariable Long id) {
        try {
            return typeOfLoanService.getTypeOfLoanById(id);
        } catch (TypeOfLoanNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping
    public TypeOfLoanDTO createTypeOfLoan(@RequestBody TypeOfLoanDTO typeOfLoanDTO) {
        return typeOfLoanService.createTypeOfLoan(typeOfLoanDTO);
    }

    @PutMapping("/{id}")
    public TypeOfLoanDTO updateTypeOfLoan(@PathVariable Long id, @RequestBody TypeOfLoanDTO typeOfLoanDTO) {
        try {
            return typeOfLoanService.updateTypeOfLoan(id, typeOfLoanDTO);
        } catch (TypeOfLoanNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTypeOfLoan(@PathVariable Long id) {
        try {
            typeOfLoanService.deleteTypeOfLoan(id);
            return ResponseEntity.ok(String.format("Type o ID %s został usunięty", id));
        } catch (TypeOfLoanNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
