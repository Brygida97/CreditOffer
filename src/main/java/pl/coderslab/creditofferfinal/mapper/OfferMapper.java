package pl.coderslab.creditofferfinal.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.creditofferfinal.dto.BankDTO;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.dto.TypeOfLoanDTO;
import pl.coderslab.creditofferfinal.entity.Bank;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OfferMapper {

    public OfferDTO toDto (Offer offer){
        OfferDTO offerDTO = new OfferDTO();
        offerDTO.setId(offer.getId());
        offerDTO.setName(offer.getName());
        offerDTO.setMinimum_amount(offer.getMinimum_amount());
        offerDTO.setMaximum_amount(offer.getMaximum_amount());
        offerDTO.setRrso(offer.getRrso());
        offerDTO.setCommission_percent(offer.getCommission_percent());
        offerDTO.setPeriod_in_months(offer.getPeriod_in_months());
        offerDTO.setUrl(offer.getUrl());

        TypeOfLoanDTO typeOfLoanDTO = new TypeOfLoanDTO();
        typeOfLoanDTO.setId(offer.getTypeOfLoan().getId());
        typeOfLoanDTO.setName_Type(offer.getTypeOfLoan().getName_Type());
        offerDTO.setTypeOfLoan(typeOfLoanDTO);

        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(offer.getBank().getId());
        bankDTO.setName(offer.getBank().getName());

        offerDTO.setBank(bankDTO);

        return offerDTO;
    }

    public Offer toEntity(OfferDTO offerDTO){
        Offer offer = new Offer();
        offer.setId(offerDTO.getId());
        offer.setName(offerDTO.getName());
        offer.setMinimum_amount(offerDTO.getMinimum_amount());
        offer.setMaximum_amount(offerDTO.getMaximum_amount());
        offer.setRrso(offerDTO.getRrso());
        offer.setCommission_percent(offerDTO.getCommission_percent());
        offer.setPeriod_in_months(offerDTO.getPeriod_in_months());
        offer.setUrl(offerDTO.getUrl());

        TypeOfLoan typeOfLoan = new TypeOfLoan();
        typeOfLoan.setId(offerDTO.getTypeOfLoan().getId());
        typeOfLoan.setName_Type(offerDTO.getTypeOfLoan().getName_Type());

        offer.setTypeOfLoan(typeOfLoan);

        Bank bank = new Bank();
        bank.setId(offerDTO.getBank().getId());
        bank.setName(offerDTO.getBank().getName());

        offer.setBank(bank);

        return offer;
    }

    public List<OfferDTO> toDtoList(List<Offer> offers) {
        return offers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
