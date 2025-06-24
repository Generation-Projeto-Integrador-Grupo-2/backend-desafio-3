package com.rebueats.rebueats.avaliacao.service;

import com.rebueats.rebueats.avaliacao.model.Avaliacao;
import com.rebueats.rebueats.avaliacao.repository.AvaliacaoRepository;
import com.rebueats.rebueats.empresa.model.Empresa;
import com.rebueats.rebueats.empresa.repository.EmpresaRepository;
import com.rebueats.rebueats.usuario.model.Usuario;
import com.rebueats.rebueats.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Avaliacao criarAvaliacao(Long empresaId, Long usuarioId, Avaliacao avaliacao) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        avaliacao.setEmpresa(empresa);
        avaliacao.setUsuario(usuario);
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listarPorEmpresa(Long empresaId) {
        return avaliacaoRepository.findByEmpresaId(empresaId);
    }

    public List<Avaliacao> listarTodas() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> buscarPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }

    public void deletar(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}
