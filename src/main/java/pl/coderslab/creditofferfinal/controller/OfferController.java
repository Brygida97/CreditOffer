package pl.coderslab.creditofferfinal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.exception.OfferNotFoundException;
import pl.coderslab.creditofferfinal.repository.OfferRepository;
import pl.coderslab.creditofferfinal.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;
    private final OfferRepository offerRepository;

    @GetMapping
    public List<OfferDTO> getAllOffer() {
        return offerService.getAllOferty();
    }

    @GetMapping("/{id}")
    public OfferDTO getOfferById(@PathVariable Long id) {
        try {
            return offerService.getOfferById(id);
        } catch (OfferNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping
    public OfferDTO createOffer(@RequestBody OfferDTO offerDTO) {
        return offerService.createOffer(offerDTO);
    }

    @PutMapping("/{id}")
    public OfferDTO updateOffer(@PathVariable Long id, @RequestBody OfferDTO offerDTO) {
        try {
            return offerService.updateOffer(id, offerDTO);
        } catch (OfferNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        try {
            offerService.deleteOffer(id);
            return ResponseEntity.ok(String.format("Oferta o ID %s została usunięta", id));
        } catch (OfferNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/lowest-rrso")
    public List<Offer> getOffersWithLowestRRSO() {
        return offerService.getOffersWithLowestRRSO(3);
    }

    @GetMapping("/lowest-commission")
    public List<Offer> getOffersWithLowestCommission() {
        return offerService.getOffersWithLowestCommission(3);
    }

    @GetMapping("/matching-offers")
    public List<Offer> getMatchingOffers() {
        try {
            return offerService.getMatchingOffersWithLowestRRSOAndCommission(3);
        } catch (OfferNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
