package pl.coderslab.creditofferfinal.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.creditofferfinal.dto.BankDTO;
import pl.coderslab.creditofferfinal.entity.Bank;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankMapper {

    public BankDTO toDto(Bank bank) {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(bank.getId());
        bankDTO.setName(bank.getName());
        return bankDTO;
    }

    public Bank toEntity(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setId(bankDTO.getId());
        bank.setName(bankDTO.getName());
        return bank;
    }


    public List<BankDTO> toDtoList(List<Bank> banks) {
        return banks.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
