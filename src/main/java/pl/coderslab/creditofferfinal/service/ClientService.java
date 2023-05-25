package pl.coderslab.creditofferfinal.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.ClientDTO;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.entity.Client;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.entity.SearchHistory;
import pl.coderslab.creditofferfinal.exception.BankNotFoundException;
import pl.coderslab.creditofferfinal.exception.ClientNotFoundException;
import pl.coderslab.creditofferfinal.mapper.ClientMapper;
import pl.coderslab.creditofferfinal.repository.ClientRepository;
import pl.coderslab.creditofferfinal.repository.OfferRepository;
import pl.coderslab.creditofferfinal.repository.SearchHistoryRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final OfferRepository offerRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    // pobranie listy wszystkich klientów
    public List<ClientDTO> getAllClient() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toDtoList(clients);
    }

    // pobranie klienta za pomocą wskazania danego ID
    public ClientDTO getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            return clientMapper.toDto(client);
        }
        throw new ClientNotFoundException("Klient o podanym ID nie istnieje");
    }

    //dodanie nowego klienta
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client createdClient = clientRepository.save(client);
        return clientMapper.toDto(createdClient);
    }

    //update danego klienta za pośrednictwem wyboru danego ID
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setFirst_Name(clientDTO.getFirst_Name());
            client.setSecond_Name(clientDTO.getSecond_Name());
            client.setEmail(clientDTO.getEmail());
            Client updatedClient = clientRepository.save(client);
            return clientMapper.toDto(updatedClient);
        }
        throw new ClientNotFoundException("Klient o podanym ID nie istnieje");
    }

    //usunięcie danego klienta rozróżniając go po ID
    public ClientDTO deleteClient(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            clientRepository.deleteById(id);
            return clientMapper.toDto(client);
        }
        throw new BankNotFoundException("Klient o podanym ID nie istnieje");
    }

    // nowe endpointy

    public void filterAndSaveSearch(ClientDTO clientDTO, BigDecimal maxAmount, BigDecimal maxRrso, BigDecimal maxCommissionPercent, Integer maxPeriodInMonths) {
        List<Offer> matchingOffers = offerRepository.findAll();

        if (maxAmount != null) {
            List<Offer> matchingOffersByMaxAmount = offerRepository.findByMaximumAmountGreaterThan(maxAmount);
            matchingOffers.retainAll(matchingOffersByMaxAmount);
        }
        if (maxCommissionPercent != null) {
            List<Offer> matchingOffersByCommissionPercent = offerRepository.findByCommissionPercentLessThan(maxCommissionPercent);
            matchingOffers.retainAll(matchingOffersByCommissionPercent);
        }
        if (maxPeriodInMonths != null) {
            List<Offer> matchingOffersByPeriodInMonths = offerRepository.findByPeriodInMonthsGreaterThan(maxPeriodInMonths);
            matchingOffers.retainAll(matchingOffersByPeriodInMonths);
        }
        if (maxRrso != null) {
            List<Offer> matchingOffersByRrso = offerRepository.findByRRSOLessThan(maxRrso);
            matchingOffers.retainAll(matchingOffersByRrso);
        }
        if (matchingOffers.isEmpty()) {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setAmount(maxAmount);
            searchHistory.setMaxRrso(maxRrso);
            searchHistory.setMaxCommissionPercent(maxCommissionPercent);
            searchHistory.setMaxPeriodInMonths(maxPeriodInMonths);
            searchHistory.setClient(clientMapper.toEntity(clientDTO));
            searchHistoryRepository.save(searchHistory);
        }
    }

    public void sendEmailIfMatchingOfferFound(ClientDTO clientDTO) {
        List<SearchHistory> searchHistories = searchHistoryRepository.findAll();

        for (SearchHistory searchHistory : searchHistories) {
            if (isMatchingOffer(searchHistory)) {
                sendEmail(searchHistory.getClient().getEmail(), searchHistory, clientDTO);
            }
        }
    }

    private boolean isMatchingOffer(SearchHistory searchHistory) {
        List<Offer> allOffers = offerRepository.findAll();

        for (Offer offer : allOffers) {
            if (isOfferMatchingFilters(offer, searchHistory)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOfferMatchingFilters(Offer offer, SearchHistory searchHistory) {
        BigDecimal maxAmount = searchHistory.getAmount();
        BigDecimal maxRrso = searchHistory.getMaxRrso();
        BigDecimal maxCommissionPercent = searchHistory.getMaxCommissionPercent();
        Integer maxPeriodInMonths = searchHistory.getMaxPeriodInMonths();

        if (maxAmount != null && offer.getMaximumAmount().compareTo(maxAmount) > 0) {
            return false;
        }
        if (maxRrso != null && offer.getRRSO().compareTo(maxRrso) > 0) {
            return false;
        }
        if (maxCommissionPercent != null && offer.getCommissionPercent().compareTo(maxCommissionPercent) > 0) {
            return false;
        }

        if (maxPeriodInMonths != null && offer.getPeriodInMonths() < maxPeriodInMonths) {
            return false;
        }
        return true;
    }

    public List<Offer> matchingOffers(SearchHistory searchHistory) {
        List<Offer> allOffers = offerRepository.findAll();

        List<Offer> matchingOffers = allOffers.stream()
                .filter(offer -> (searchHistory.getAmount() == null || offer.getMaximumAmount().compareTo(searchHistory.getAmount()) > 0))
                .filter(offer -> (searchHistory.getMaxRrso() == null || offer.getRRSO().compareTo(searchHistory.getMaxRrso()) <= 0))
                .filter(offer -> (searchHistory.getMaxCommissionPercent() == null || offer.getCommissionPercent().compareTo(searchHistory.getMaxCommissionPercent()) <= 0))
                .filter(offer -> (searchHistory.getMaxPeriodInMonths() == null || offer.getPeriodInMonths() >= searchHistory.getMaxPeriodInMonths()))
                .collect(Collectors.toList());

        return matchingOffers;
    }


    public void sendEmail(String emailAddress, SearchHistory searchHistory, ClientDTO clientDTO) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("creditofferfinal@gmail.com", "creditoffer");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_email@example.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(clientDTO.getEmail()));
            message.setSubject("Nowa oferta spełniająca warunki");

            List<Offer> matchingOffers = matchingOffers(searchHistory);

            StringBuilder emailContentBuilder = new StringBuilder();
            emailContentBuilder.append("Drogi Kliencie,\n\nMamy dla Ciebie dobre wiadomości! Znaleźliśmy oferty, które spełniają Twoje warunki wyszukiwania:\n\n");

            for (Offer matchingOffer : matchingOffers) {
                emailContentBuilder.append("Oferta: ").append(matchingOffer.getName()).append("\n");
                emailContentBuilder.append("Kwota: ").append(matchingOffer.getMinimumAmount()).append(" - ").append(matchingOffer.getMaximumAmount()).append("\n");
                emailContentBuilder.append("RRSO: ").append(matchingOffer.getRRSO()).append("\n");
                emailContentBuilder.append("Prowizja: ").append(matchingOffer.getCommissionPercent()).append("\n");
                emailContentBuilder.append("Okres: ").append(matchingOffer.getPeriodInMonths()).append(" miesiące/miesięcy\n");
                emailContentBuilder.append("URL: ").append(matchingOffer.getUrl()).append("\n");
                emailContentBuilder.append("Bank: ").append(matchingOffer.getBank().getName()).append("\n");
                emailContentBuilder.append("Typ pożyczki: ").append(matchingOffer.getTypeOfLoan().getName_Type().concat("\n\n"));
            }

            emailContentBuilder.append("Zapraszamy do skorzystania z naszych ofert.\n\n");
            emailContentBuilder.append("Pozdrawiamy,\nTwoja wyszukiwarka ofert kredytowych");

            String emailContent = emailContentBuilder.toString();

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error message: " + e.getMessage());
        }
    }
}