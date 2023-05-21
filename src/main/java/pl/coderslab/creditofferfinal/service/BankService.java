package pl.coderslab.creditofferfinal.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.BankDTO;
import pl.coderslab.creditofferfinal.entity.Bank;
import pl.coderslab.creditofferfinal.exception.BankNotFoundException;
import pl.coderslab.creditofferfinal.mapper.BankMapper;
import pl.coderslab.creditofferfinal.repository.BankRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    // pobranie listy wszystkich banków
    public List<BankDTO> getAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        return bankMapper.toDtoList(banks);
    }

    // pobranie banku za pomocą wskazania danego ID
    public BankDTO getBankById(Long id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()){
            Bank bank = optionalBank.get();
            return bankMapper.toDto(bank);
        }
        throw new BankNotFoundException("Bank o podanym ID nie istnieje");
    }

    //dodanie nowego banku
    public BankDTO createBank(BankDTO bankDTO) {
        Bank bank=bankMapper.toEntity(bankDTO);
        Bank createdBank = bankRepository.save(bank);
        return bankMapper.toDto(createdBank);
    }

    //update danego banku za pośrednictwem wyboru danego ID
    public BankDTO updateBank(Long id, BankDTO bankDTO) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            Bank bank = optionalBank.get();
            bank.setName(bankDTO.getName());
            Bank updatedBank = bankRepository.save(bank);
            return bankMapper.toDto(updatedBank);
        }
        throw new BankNotFoundException("Bank o podanym ID nie istnieje");
    }

    //usunięcie danego banku rozróżniając go po ID
    public BankDTO deleteBank(Long id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            Bank bank = optionalBank.get();
            bankRepository.deleteById(id);
            return bankMapper.toDto(bank);
        }
        throw new BankNotFoundException("Bank o podanym ID nie istnieje");
    }
}
