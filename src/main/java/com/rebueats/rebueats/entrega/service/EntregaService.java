package com.rebueats.rebueats.entrega.service;

import com.rebueats.rebueats.entrega.model.Entrega;
import com.rebueats.rebueats.entrega.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public List<Entrega> listarTodas() {
        return entregaRepository.findAll();
    }

    public Optional<Entrega> buscarPorId(Long id) {
        return entregaRepository.findById(id);
    }

    public List<Entrega> listarPorUsuario(Long usuarioId) {
        return entregaRepository.findByUsuarioId(usuarioId);
    }

    public List<Entrega> listarPorMotoboy(Long motoboyId) {
        return entregaRepository.findByMotoboyId(motoboyId);
    }

    public List<Entrega> listarPorEmpresa(Long empresaId) {
        return entregaRepository.findByEmpresaId(empresaId);
    }

    public Entrega salvar(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public void deletar(Long id) {
        entregaRepository.deleteById(id);
    }
}
