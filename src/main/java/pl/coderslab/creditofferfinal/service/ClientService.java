package pl.coderslab.creditofferfinal.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.creditofferfinal.dto.ClientDTO;
import pl.coderslab.creditofferfinal.entity.Client;
import pl.coderslab.creditofferfinal.exception.BankNotFoundException;
import pl.coderslab.creditofferfinal.exception.ClientNotFoundException;
import pl.coderslab.creditofferfinal.mapper.ClientMapper;
import pl.coderslab.creditofferfinal.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    // pobranie listy wszystkich klientów
    public List<ClientDTO> getAllClient() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toDtoList(clients);
    }

    // pobranie klienta za pomocą wskazania danego ID
    public ClientDTO getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()){
            Client client = optionalClient.get();
            return clientMapper.toDto(client);
        }
        throw new ClientNotFoundException("Klient o podanym ID nie istnieje");
    }

    //dodanie nowego klienta
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client=clientMapper.toEntity(clientDTO);
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

}
