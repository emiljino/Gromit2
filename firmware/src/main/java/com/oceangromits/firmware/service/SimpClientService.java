package com.oceangromits.firmware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SimpClientService { //simple clients, without authorisation to stream video

    private final SimpUserRegistry simpUserRegistry;

    @Autowired
    public SimpClientService(SimpUserRegistry simpUserRegistry) {
        this.simpUserRegistry = simpUserRegistry;
    }

    public int getClientCount() {
        return simpUserRegistry.getUserCount();
    }

    public List<Principal> getClients() {
        Set<SimpUser> users = simpUserRegistry.getUsers();
        return users.stream()
                .map(SimpUser::getPrincipal)
                .collect(Collectors.toList());
    }


}
