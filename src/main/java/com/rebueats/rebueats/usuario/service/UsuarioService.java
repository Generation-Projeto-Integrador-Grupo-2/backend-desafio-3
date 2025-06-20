package com.rebueats.rebueats.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.rebueats.rebueats.security.JwtService;
import com.rebueats.rebueats.usuario.model.Usuario;
import com.rebueats.rebueats.usuario.model.UsuarioLogin;
import com.rebueats.rebueats.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Esse Email já foi utilizado");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<UsuarioLogin> autenticarUsuario(UsuarioLogin usuarioLogin) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.getEmail());

        if (usuario.isPresent()) {
            if (passwordEncoder.matches(usuarioLogin.getSenha(), usuario.get().getSenha())) {

                String token = jwtService.generateToken(usuario.get());

                usuarioLogin.setId(usuario.get().getId());
                usuarioLogin.setNome(usuario.get().getName());
                usuarioLogin.setToken(token);

                return Optional.of(usuarioLogin);
            }
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!");
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> listarPorId(Long id) {
        return usuarioRepository.findById(id);
    }


    public Optional<Usuario> atualizarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getId());

        if (usuarioExistente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioComMesmoEmail.isPresent()
                && !usuarioComMesmoEmail.get().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Este Email já está em uso por outro usuário");
        }

        Usuario usuarioAtualizado = usuarioExistente.get();
        usuarioAtualizado.setName(usuario.getName());
        usuarioAtualizado.setEmail(usuario.getEmail());

        if (!passwordEncoder.matches(usuario.getSenha(), usuarioAtualizado.getSenha())) {
            usuarioAtualizado.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        return Optional.of(usuarioRepository.save(usuarioAtualizado));
    }


    public void deletar(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse Usuario não existe");
        }

        usuarioRepository.deleteById(id);
    }
}
