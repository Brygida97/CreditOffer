package pl.coderslab.creditofferfinal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.service.MailService;
import pl.coderslab.creditofferfinal.service.OfferService;

@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping
    public ResponseEntity<OfferDTO> createOfferAndSenMessage(@RequestBody OfferDTO offerDTO) {
        OfferDTO createdOffer = mailService.createOfferAndSenMessage(offerDTO);
        return ResponseEntity.ok(createdOffer);
    }
}
