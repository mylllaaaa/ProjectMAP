package com.myllamedeiros.projectmap.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	@Autowired
    private UserService service;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestParam("matricula") String matricula, @RequestParam("senha") String senha) {
    	User user = service.findById(matricula);

        if (user == null || !user.getSenha().equals(senha)) {
            return ResponseEntity.status(401).body("Matrícula ou senha incorretos");
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("matricula", user.getMatricula());
        response.put("nomeDeUsuario", user.getNomeDeUsuario());
        response.put("campus", user.getCampus().toString());
        response.put("token", user.getMatricula());
        
        return ResponseEntity.ok(response);
    }
}
