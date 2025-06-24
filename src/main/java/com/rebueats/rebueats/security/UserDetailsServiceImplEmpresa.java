package com.rebueats.rebueats.security;

import com.rebueats.rebueats.empresa.model.Empresa;
import com.rebueats.rebueats.empresa.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service("empresaDetailsService")
public class UserDetailsServiceImplEmpresa implements UserDetailsService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Empresa> empresa = empresaRepository.findByEmail(email);
        if (empresa.isPresent())
            return new UserDetailsImplEmpresa(empresa.get());
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}
