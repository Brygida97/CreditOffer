package pl.coderslab.creditofferfinal.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.exception.OfferNotFoundException;
import pl.coderslab.creditofferfinal.mapper.OfferMapper;
import pl.coderslab.creditofferfinal.repository.OfferRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    public List<OfferDTO> getAllOferty() {
        List<Offer> offers = offerRepository.findAll();
        return offerMapper.toDtoList(offers);
    }

    public OfferDTO getOfferById(Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()){
            Offer offer = optionalOffer.get();
            return offerMapper.toDto(offer);
        }
        throw new OfferNotFoundException("Oferta o podanym ID nie istnieje");
    }

    public OfferDTO createOffer(OfferDTO offerDTO) {
        Offer offer = offerMapper.toEntity(offerDTO);
        Offer createdOffer = offerRepository.save(offer);
        return offerMapper.toDto(createdOffer);
    }

    public OfferDTO updateOffer(Long id, OfferDTO offerDTO) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            offer.setName(offerDTO.getName());
            offer.setMinimumAmount(offerDTO.getMinimumAmount());
            offer.setMaximumAmount(offerDTO.getMaximumAmount());
            offer.setRRSO(offerDTO.getRRSO());
            offer.setCommissionPercent(offerDTO.getCommissionPercent());
            offer.setPeriodInMonths(offerDTO.getPeriodInMonths());
            offer.setUrl(offerDTO.getUrl());
            Offer updatedOffer = offerRepository.save(offer);
            return offerMapper.toDto(updatedOffer);
        }
        throw new OfferNotFoundException("Oferta o podanym ID nie istnieje");
    }

    public OfferDTO deleteOffer(Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            offerRepository.deleteById(id);
            return offerMapper.toDto(offer);
        }
        throw new OfferNotFoundException("Oferta o podanym ID nie istnieje");
    }


    public List<Offer> getOffersWithLowestRRSO(int limit) {
        List<Offer> allOffers = offerRepository.findAll();

        List<Offer> offersWithLowestRRSO = allOffers.stream()
                .sorted(Comparator.comparing(Offer::getRRSO))
                .limit(limit)
                .collect(Collectors.toList());

        return offersWithLowestRRSO;
    }

    public List<Offer> getOffersWithLowestCommission(int limit) {
        List<Offer> allOffers = offerRepository.findAll();

        List<Offer> offersWithLowestCommission = allOffers.stream()
                .sorted(Comparator.comparing(Offer::getCommissionPercent))
                .limit(limit)
                .collect(Collectors.toList());

        return offersWithLowestCommission;
    }

    public List<Offer> getMatchingOffersWithLowestRRSOAndCommission(int limit) {
        List<Offer> lowestRRSOOffers = getOffersWithLowestRRSO(limit);
        List<Offer> lowestCommissionOffers = getOffersWithLowestCommission(limit);

        List<Offer> matchingOffers = lowestRRSOOffers.stream()
                .filter(lowestCommissionOffers::contains)
                .collect(Collectors.toList());

        return matchingOffers;
    }
}
