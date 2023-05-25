package pl.coderslab.creditofferfinal.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.creditofferfinal.dto.ClientDTO;
import pl.coderslab.creditofferfinal.exception.BankNotFoundException;
import pl.coderslab.creditofferfinal.exception.ClientNotFoundException;
import pl.coderslab.creditofferfinal.exception.OfferNotFoundException;
import pl.coderslab.creditofferfinal.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClient();
        return clients;
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        try {
            return clientService.getClientById(id);
        } catch (OfferNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }


    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        try {
            return clientService.updateClient(id, clientDTO);
        } catch (ClientNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok(String.format("Klient o ID %s został usunięty", id));
        } catch (BankNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}

