package pl.coderslab.creditofferfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.creditofferfinal.dto.BankDTO;
import pl.coderslab.creditofferfinal.service.BankService;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    private BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    // pobranie listy wszystkich banków
    @GetMapping
    public List<BankDTO> getAllBanks() {
        return bankService.getAllBanks();
    }

    // pobranie banku za pomocą wskazania danego ID
    @GetMapping("/{id}")
    public BankDTO getBankById(@PathVariable Long id) {
        return bankService.getBankById(id);
    }

    //dodanie nowego banku
    @PostMapping
    public BankDTO createBank(@RequestBody BankDTO bankDTO) {
        return bankService.createBank(bankDTO);
    }

    //update danego banku za pośrednictwem wyboru danego ID
    @PutMapping("/{id}")
    public BankDTO updateBank(@PathVariable Long id, @RequestBody BankDTO bankDTO) {
        return bankService.updateBank(id, bankDTO);
    }

    //usunięcie danego banku rozróżniając go po ID
    @DeleteMapping("/{id}")
    public void deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
    }
}
