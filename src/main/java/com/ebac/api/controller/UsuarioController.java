package com.ebac.api.controller;

import com.ebac.api.model.Telefono;
import com.ebac.api.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static AtomicInteger idCounter = new AtomicInteger(1);

    static {
        Telefono tel1 = Telefono.builder()
                .idTelefono(1).tipoTelefono("Casa").lada(55).numero("12345678").build();
        Telefono tel2 = Telefono.builder()
                .idTelefono(2).tipoTelefono("Celular").lada(55).numero("87654321").build();

        usuarios.add(Usuario.builder()
                .idUsuario(1).nombre("Azul").edad(27)
                .telefonos(List.of(tel1, tel2)).build());
        usuarios.add(Usuario.builder()
                .idUsuario(2).nombre("Isabella").edad(25)
                .telefonos(List.of(tel1)).build());

        idCounter.set(3);
    }

    private Map<String, Object> buildResponse(boolean success, String message, Object body, int statusCode) {
        Map<String, Object> responseEntity = new LinkedHashMap<>();
        responseEntity.put("headers", new HashMap<>());
        responseEntity.put("body", body);
        responseEntity.put("statusCode", statusCode == 200 ? "OK" : "BAD_REQUEST");
        responseEntity.put("statusCodeValue", statusCode);

        Map<String, Object> wrapper = new LinkedHashMap<>();
        wrapper.put("success", success);
        wrapper.put("message", message);
        wrapper.put("responseEntity", responseEntity);

        return wrapper;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getUsuarios() {
        log.info("Obteniendo todos los usuarios");
        return ResponseEntity.ok(buildResponse(true, "OK", usuarios, 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUsuarioById(@PathVariable int id) {
        log.info("Obteniendo usuario con id {}", id);
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getIdUsuario() == id)
                .findFirst();

        if (usuario.isPresent()) {
            return ResponseEntity.ok(buildResponse(true, "OK", usuario.get(), 200));
        }
        return ResponseEntity.ok(buildResponse(false, "Usuario no encontrado", null, 404));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUsuario(@RequestBody Usuario usuario) {
        log.info("Creando usuario {}", usuario.getNombre());
        usuario.setIdUsuario(idCounter.getAndIncrement());
        usuarios.add(usuario);
        return ResponseEntity.ok(buildResponse(true, "Usuario creado exitosamente", usuario, 200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        log.info("Actualizando usuario con id {}", id);
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdUsuario() == id) {
                usuario.setIdUsuario(id);
                usuarios.set(i, usuario);
                return ResponseEntity.ok(buildResponse(true, "Usuario actualizado", usuario, 200));
            }
        }
        return ResponseEntity.ok(buildResponse(false, "Usuario no encontrado", null, 404));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUsuario(@PathVariable int id) {
        log.info("Eliminando usuario con id {}", id);
        usuarios.removeIf(u -> u.getIdUsuario() == id);
        return ResponseEntity.ok(buildResponse(true, "Usuario eliminado", null, 200));
    }
}
