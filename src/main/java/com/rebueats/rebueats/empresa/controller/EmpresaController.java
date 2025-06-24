package com.rebueats.rebueats.empresa.controller;

import com.rebueats.rebueats.empresa.model.Empresa;
import com.rebueats.rebueats.empresa.model.EmpresaLogin;
import com.rebueats.rebueats.empresa.service.EmpresaService;
import com.rebueats.rebueats.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<Empresa>> listarTodas() {
        return ResponseEntity.ok(empresaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
        return empresaService.buscarPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> criar(@RequestBody Empresa empresa) {
        return ResponseEntity.ok(empresaService.salvar(empresa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        if (!empresaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        empresa.setId(id);
        return ResponseEntity.ok(empresaService.salvar(empresa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!empresaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmpresaLogin empresaLogin) {
        return empresaService.autenticar(empresaLogin).map(empresa -> {
            String token = jwtService.generateTokenEmpresa(empresa);
            empresaLogin.setId(empresa.getId());
            empresaLogin.setNome(empresa.getNome());
            empresaLogin.setSenha(null);
            return ResponseEntity.ok().header("Authorization", token).body(empresaLogin);
        }).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
