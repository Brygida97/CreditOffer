package pl.coderslab.creditofferfinal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.exception.OfferNotFoundException;
import pl.coderslab.creditofferfinal.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    // pobranie listy wszystkich ofert
    @GetMapping
    public List<OfferDTO> getAllOferty() {
        return offerService.getAllOferty();
    }

    // pobranie ofert za pomocą wskazania danego ID
    @GetMapping("/{id}")
    public OfferDTO getOfertaById(@PathVariable Long id) {
        try{
            return offerService.getOfferById(id);
        }catch (OfferNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //dodanie nowej oferty
    @PostMapping
    public OfferDTO createOffer(@RequestBody OfferDTO offerDTO) {
        return offerService.createOffer(offerDTO);
    }

    //update danej oferty za pośrednictwem wyboru danego ID
    @PutMapping("/{id}")
    public OfferDTO updateOffer(@PathVariable Long id, @RequestBody OfferDTO offerDTO) {
        try {
            return offerService.updateOffer(id, offerDTO);
        }catch (OfferNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //usunięcie danej oferty rozróżniając go po ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        try {
            offerService.deleteOffer(id);
            return ResponseEntity.ok(String.format("Oferta o ID %s została usunięta",id));
        }catch (OfferNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

}

