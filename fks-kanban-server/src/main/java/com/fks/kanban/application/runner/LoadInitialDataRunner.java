package com.fks.kanban.application.runner;

import com.fks.kanban.domain.model.ClientDetails;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.ClientDetailsRepository;
import com.fks.kanban.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class LoadInitialDataRunner implements ApplicationRunner {

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initSecurityData();
    }

    @Transactional
    private void initSecurityData() {

        var encoder = new BCryptPasswordEncoder();

        var client = new ClientDetails(
                "kanban-ui",
                encoder.encode("12345"),
                "read,write",
                "password,refresh_token",
                3600,
                3600
        );

        clientDetailsRepository.save(client);

        var kim = new Usuario(
                "omenakim",
                encoder.encode("12345")
        );

        usuarioRepository.save(kim);

        var franklin = new Usuario(
                "fkazeredo",
                encoder.encode("12345")
        );

        usuarioRepository.save(franklin);

        var julio = new Usuario(
                "omenajulio",
                encoder.encode("12345")
        );

        usuarioRepository.save(julio);

    }
}
