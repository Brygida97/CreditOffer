package pl.coderslab.creditofferfinal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.creditofferfinal.dto.BankDTO;
import pl.coderslab.creditofferfinal.exception.BankNotFoundException;
import pl.coderslab.creditofferfinal.service.BankService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banks")
@AllArgsConstructor
public class BankController {

    private BankService bankService;

    // pobranie listy wszystkich banków
    @GetMapping
    public List<BankDTO> getAllBanks() {
        List<BankDTO> banks = bankService.getAllBanks();
        return banks;
    }

    // pobranie banku za pomocą wskazania danego ID
    @GetMapping("/{id}")
    public BankDTO getBankById(@PathVariable Long id) {
        try {
            return bankService.getBankById(id);
        }catch (BankNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //dodanie nowego banku
    @PostMapping
    public BankDTO createBank(@RequestBody BankDTO bankDTO) {
        return bankService.createBank(bankDTO);
    }

    //update danego banku za pośrednictwem wyboru danego ID
    @PutMapping("/{id}")
    public BankDTO updateBank(@PathVariable Long id, @RequestBody BankDTO bankDTO) {
        try {
            return bankService.updateBank(id, bankDTO);
        }catch (BankNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //usunięcie danego banku rozróżniając go po ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBank(@PathVariable Long id) {
        try {
            bankService.deleteBank(id);
            return ResponseEntity.ok(String.format("Bank o ID %s został usunięty",id));
        }catch (BankNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @GetMapping("/offers/count")
    public ResponseEntity<List<Map<String, Object>>> getBankOfferCounts() {
        List<Map<String, Object>> bankOfferCounts = bankService.getBankOfferCounts();
        return ResponseEntity.ok(bankOfferCounts);
    }


}
