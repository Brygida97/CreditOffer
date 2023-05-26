package pl.coderslab.creditofferfinal.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.entity.Client;
import pl.coderslab.creditofferfinal.entity.Offer;
import pl.coderslab.creditofferfinal.mapper.OfferMapper;
import pl.coderslab.creditofferfinal.repository.ClientRepository;
import pl.coderslab.creditofferfinal.repository.OfferRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;
    private OfferMapper offerMapper;
    private OfferRepository offerRepository;

    private ClientRepository clientRepository;


    public void sendSimpleEmail(String to, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom("Blog Example <CreditOffer1@outlook.com>");

        msg.setSubject("Nowa oferta kredytowa");
        msg.setText(content);

        javaMailSender.send(msg);
    }

    public String generateEmailContent(OfferDTO offerDTO) {
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
        emailContentBuilder.append("Zapraszamy do skorzystania z naszych ofert.\n\n");
        emailContentBuilder.append("Pozdrawiamy,\nTwoja wyszukiwarka ofert kredytowych");

        return emailContentBuilder.toString();
    }

    public int sendNewOfferNotificationEmail(OfferDTO offerDTO) {
        String emailContent = generateEmailContent(offerDTO);

        List<Client> clients = clientRepository.findAll();
        int sentEmailCount = 0;

        for (Client client : clients){
            String recipientEmail = client.getEmail();
            sendSimpleEmail(recipientEmail, emailContent);
            sentEmailCount++;
        }

        return sentEmailCount;
    }


    public Offer createOfferAndSenMessage(OfferDTO offerDTO) {
        Offer offer = offerMapper.toEntity(offerDTO);
        Offer createdOffer = offerRepository.save(offer);
        OfferDTO createdOfferDTO = offerMapper.toDto(createdOffer);

        sendNewOfferNotificationEmail(createdOfferDTO);

        return createdOffer;
    }

}