package com.rebueats.rebueats.motoboy.service;

import com.rebueats.rebueats.motoboy.model.Motoboy;
import com.rebueats.rebueats.motoboy.repository.MotoboyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoboyService {

    @Autowired
    private MotoboyRepository motoboyRepository;

    public List<Motoboy> listarTodos() {
        return motoboyRepository.findAll();
    }

    public Optional<Motoboy> buscarPorId(Long id) {
        return motoboyRepository.findById(id);
    }

    public Motoboy salvar(Motoboy motoboy) {
        return motoboyRepository.save(motoboy);
    }

    public void deletar(Long id) {
        motoboyRepository.deleteById(id);
    }
}
