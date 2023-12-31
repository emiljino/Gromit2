package com.oceangromits.firmware.controller.api;

import com.oceangromits.firmware.model.Client;
import com.oceangromits.firmware.model.TokenMessage;
import com.oceangromits.firmware.service.ClientService;
import com.oceangromits.firmware.service.SimpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ClientService clientService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final SimpClientService simpClientService;

    @Autowired
    public AdminController(ClientService clientService,
                           SimpMessagingTemplate simpMessagingTemplate,
                           SimpClientService simpClientService) {
        this.clientService = clientService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.simpClientService = simpClientService;
    }

    /*
    Logs a user in as an admin, returns a Admin user token
     */
    @PostMapping("/login")
    public TokenMessage login(@RequestBody Client client) {
        return new TokenMessage(client.getName(), clientService.signIn(client.getName(), client.getPassword()));
    }

    /*
    Authorizes another client, allowing them to connect to the stream
    And allows streaming their video as well as receiving
     */
    @PostMapping("/authorize_client")
    public Client authorizeClient(@RequestBody Client client) {
        TokenMessage message = new TokenMessage(client.getName(), clientService.genClientVideoToken(client.getName()));
        simpMessagingTemplate.convertAndSendToUser(client.getName(), "/queue/message", message);
        return client;
    }

    @GetMapping("/clients") //lists the current clients
    public List<Principal> listClients() {
        return simpClientService.getClients();
    }

    @PostMapping("/reset") //resets the database by dropping it, causing all previous permissions to reset
    public void resetServer() {
        clientService.resetServerDangerously();
    }


}
