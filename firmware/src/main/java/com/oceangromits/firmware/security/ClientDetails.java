package com.oceangromits.firmware.security;

import com.oceangromits.firmware.model.Client;
import com.oceangromits.firmware.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientDetails implements UserDetailsService { //checks client against usernames in the repo

    private final ClientRepository clientRepository;

    @Autowired
    public ClientDetails(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Client client = clientRepository.findByName(username);

        if (Objects.isNull(client)) {
            throw new UsernameNotFoundException("User not found");
        }

        return User
                .withUsername(username)
                .password(client.getPassword())
                .authorities(client.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
