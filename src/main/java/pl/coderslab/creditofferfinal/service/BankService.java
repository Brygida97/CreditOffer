package pl.coderslab.creditofferfinal.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.BankDTO;
import pl.coderslab.creditofferfinal.entity.Bank;
import pl.coderslab.creditofferfinal.exception.BankNotFoundException;
import pl.coderslab.creditofferfinal.mapper.BankMapper;
import pl.coderslab.creditofferfinal.repository.BankRepository;
import pl.coderslab.creditofferfinal.repository.OfferRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;
    private final OfferRepository offerRepository;

    public List<BankDTO> getAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        return bankMapper.toDtoList(banks);
    }

    public BankDTO getBankById(Long id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            Bank bank = optionalBank.get();
            return bankMapper.toDto(bank);
        }
        throw new BankNotFoundException("Bank o podanym ID nie istnieje");
    }

    public BankDTO createBank(BankDTO bankDTO) {
        Bank bank = bankMapper.toEntity(bankDTO);
        Bank createdBank = bankRepository.save(bank);
        return bankMapper.toDto(createdBank);
    }

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

    public BankDTO deleteBank(Long id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            Bank bank = optionalBank.get();
            bankRepository.deleteById(id);
            return bankMapper.toDto(bank);
        }
        throw new BankNotFoundException("Bank o podanym ID nie istnieje");
    }

    public List<Map<String, Object>> getBankOfferCounts() {
        return bankRepository.findAll().stream()
                .map(bank -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("bankId", bank.getId());
                    result.put("bankName", bank.getName());
                    result.put("offerCount", offerRepository.countByBankId((long) Math.toIntExact(bank.getId())));
                    return result;
                })
                .collect(Collectors.toList());
    }
}
