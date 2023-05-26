package pl.coderslab.creditofferfinal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.service.MailService;
import pl.coderslab.creditofferfinal.service.OfferService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping
    public ResponseEntity<String> createOfferAndSenMessage(@RequestBody OfferDTO offerDTO) {
        Offer createdOffer = mailService.createOfferAndSenMessage(offerDTO);
        int sentEmailCount = mailService.sendNewOfferNotificationEmail(offerDTO);
        String responseMessage = String.format("Zostało wysłanych %s maili. Z ofertą - %s", sentEmailCount, createdOffer.toString());

        return ResponseEntity.ok(responseMessage);
    }

}