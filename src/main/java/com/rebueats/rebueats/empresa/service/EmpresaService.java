package com.rebueats.rebueats.empresa.service;

import com.rebueats.rebueats.empresa.model.Empresa;
import com.rebueats.rebueats.empresa.model.EmpresaLogin;
import com.rebueats.rebueats.empresa.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }

    public Optional<Empresa> autenticar(EmpresaLogin empresaLogin) {
        Optional<Empresa> empresaOpt = empresaRepository.findByEmail(empresaLogin.getEmail());
        if (empresaOpt.isPresent()
                && passwordEncoder.matches(empresaLogin.getSenha(), empresaOpt.get().getSenha())) {
            return empresaOpt;
        }
        return Optional.empty();
    }
}
