package pl.coderslab.creditofferfinal.mapper;

import org.springframework.stereotype.Component;
import pl.coderslab.creditofferfinal.dto.ClientDTO;
import pl.coderslab.creditofferfinal.dto.OfferDTO;
import pl.coderslab.creditofferfinal.entity.Client;
import pl.coderslab.creditofferfinal.entity.Offer;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public ClientDTO toDto(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setFirst_Name(client.getFirst_Name());
        clientDTO.setSecond_Name(client.getSecond_Name());
        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }

    public Client toEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setFirst_Name(clientDTO.getFirst_Name());
        client.setSecond_Name(clientDTO.getSecond_Name());
        client.setEmail(clientDTO.getEmail());
        return client;
    }
    public List<ClientDTO> toDtoList(List<Client> clients) {
        return clients.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}