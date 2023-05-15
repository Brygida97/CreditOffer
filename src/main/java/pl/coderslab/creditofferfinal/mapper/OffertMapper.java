package pl.coderslab.creditofferfinal.mapper;

import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.TypeOfLoan;
import pl.coderslab.creditofferfinal.repository.BankRepository;
import pl.coderslab.creditofferfinal.repository.TypeOfLoanRepository;

public class OffertMapper {

    public static OfferDTO toDTO (Offer offer){
        OfferDTO offerDTO = new OfferDTO();
        offerDTO.setId(offer.getId());
        offerDTO.setName(offer.getName());
        offerDTO.setMinimum_amount(offer.getMinimum_amount());
        offerDTO.setMaximum_amount(offer.getMaximum_amount());
        offerDTO.setRrso(offer.getRrso());
        offerDTO.setCommission_percent(offer.getCommission_percent());
        offerDTO.setPeriod_in_months(offer.getPeriod_in_months());
        offerDTO.setUrl(offer.getUrl());
        offerDTO.setBankId(offer.getBank().getId());
        offerDTO.setTypeOfLoan(offer.getTypeOfLoan());
        return offerDTO;
    }

    public static Offer toEntity(OfferDTO offerDTO){
        Offer offer = new Offer();
        offer.setId(offerDTO.getId());
        offer.setName(offerDTO.getName());
        offer.setMinimum_amount(offerDTO.getMinimum_amount());
        offer.setMaximum_amount(offerDTO.getMaximum_amount());
        offer.setRrso(offerDTO.getRrso());
        offer.setCommission_percent(offerDTO.getCommission_percent());
        offer.setPeriod_in_months(offerDTO.getPeriod_in_months());
        offer.setUrl(offerDTO.getUrl());
        offer.setBank(offerDTO.getBankId().longValue());
        offer.setTypeOfLoan(offerDTO.getTypeOfLoan());
        return offer;
    }
}
