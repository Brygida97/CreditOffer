package pl.coderslab.creditofferfinal.mapper;

import pl.coderslab.creditofferfinal.dto.ClientDTO;
import pl.coderslab.creditofferfinal.entity.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setImie(client.getImie());
        clientDTO.setNazwisko(client.getNazwisko());
        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }

    public static Client toEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setImie(clientDTO.getImie());
        client.setNazwisko(clientDTO.getNazwisko());
        client.setEmail(clientDTO.getEmail());
        return client;
    }
}