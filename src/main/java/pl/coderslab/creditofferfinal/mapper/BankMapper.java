package pl.coderslab.creditofferfinal.mapper;

import pl.coderslab.creditofferfinal.dto.BankDTO;
import pl.coderslab.creditofferfinal.entity.Bank;

public class BankMapper {

    public static BankDTO toDto(Bank bank){
        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(bank.getId());
        bankDTO.setName(bankDTO.getName());
        return bankDTO;
    }

    public static Bank toEntity(BankDTO bankDTO){
        Bank bank = new Bank();
        bank.setId(bankDTO.getId());
        bank.setName(bankDTO.getName());
        return bank;
    }
}
