package pl.coderslab.creditofferfinal.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.entity.Client;
import pl.coderslab.creditofferfinal.entity.SearchHistory;
import pl.coderslab.creditofferfinal.mapper.OfferMapper;
import pl.coderslab.creditofferfinal.repository.ClientRepository;
import pl.coderslab.creditofferfinal.repository.SearchHistoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Getter
public class SendMessageWhenOfferIsMatchingService {

    private final MailService mailService;
    private final OfferMapper offerMapper;
    private final OfferService offerService;
    private final ClientRepository clientRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    public String sendMessageWhenOfferIsMatching(OfferDTO offerDTO) {
        List<SearchHistory> searchHistoryList = searchHistoryRepository.findAll();
        boolean isMatchingOfferFound = false;

        for (SearchHistory searchHistory : searchHistoryList) {
            if (isOfferMatchingFilters(offerDTO, searchHistory)) {
                isMatchingOfferFound = true;
                Client client = searchHistory.getClient();
                String emailContent = generateEmailContent(offerDTO);
                mailService.sendSimpleEmail(client.getEmail(), emailContent);
                searchHistoryRepository.delete(searchHistory);
            }
        }

        offerService.createOffer(offerDTO);

        if (isMatchingOfferFound) {
            return "Dodano nową ofertę oraz wysłano wiadomości do Klientów - nowa oferta została sparowana z wyszukiwaniami Klientów, które nie pasowały do żadnej oferty.";
        } else {
            return "Dodano nową ofertę - brak dopasowań z zapisywanymi wyszukiwaniami. Nie wysłano żadnego maila.";
        }
    }


    private boolean isOfferMatchingFilters(OfferDTO offerDTO, SearchHistory searchHistory) {
        return (offerDTO.getMaximumAmount().compareTo(searchHistory.getAmount()) >= 0)
                && (offerDTO.getRRSO().compareTo(searchHistory.getMaxRrso()) <= 0)
                && (offerDTO.getCommissionPercent().compareTo(searchHistory.getMaxCommissionPercent()) <= 0)
                && (offerDTO.getPeriodInMonths() >= searchHistory.getMaxPeriodInMonths());
    }

    private String generateEmailContent(OfferDTO offerDTO) {
        StringBuilder emailContentBuilder = new StringBuilder();
        emailContentBuilder.append("Drogi Kliencie,\n\nMamy dla Ciebie dobre wiadomości - dodaliśmy nową ofertę kredytową do wyszukiwarki.\n\n");
        emailContentBuilder.append("Oto kilka szczegółów o niej:\n");
        emailContentBuilder.append("Nazwa oferty: ").append(offerDTO.getName()).append("\n");
        emailContentBuilder.append("Minimalna kwota: ").append(offerDTO.getMinimumAmount()).append("\n");
        emailContentBuilder.append("Maksymalna kwota: ").append(offerDTO.getMaximumAmount()).append("\n");
        emailContentBuilder.append("RRSO: ").append(offerDTO.getRRSO()).append("\n");
        emailContentBuilder.append("Prowizja: ").append(offerDTO.getCommissionPercent()).append("\n");
        emailContentBuilder.append("Maksymalny okres kredytowania: ").append(offerDTO.getPeriodInMonths()).append("\n");
        emailContentBuilder.append("Link do oferty: ").append(offerDTO.getUrl()).append("\n");
        emailContentBuilder.append("Bank: ").append(offerDTO.getBank().getName()).append("\n");
        emailContentBuilder.append("Typ oferty: ").append(offerDTO.getTypeOfLoan().getName_Type()).append("\n\n");
        emailContentBuilder.append("Zapraszamy do zapoznania się z nią!\n\n");
        emailContentBuilder.append("Pozdrawiamy,\nTwoja wyszukiwarka ofert kredytowych");

        return emailContentBuilder.toString();
    }
}
