package pl.coderslab.creditofferfinal.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.service.SendMessageWhenOfferIsMatchingService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SendMessageWhenOfferIsMatchingController {
    private final SendMessageWhenOfferIsMatchingService sendMessageService;

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessageWhenOfferIsMatching(@RequestBody OfferDTO offerDTO) {
        String message = sendMessageService.sendMessageWhenOfferIsMatching(offerDTO);
        return ResponseEntity.ok(message);
    }
}

