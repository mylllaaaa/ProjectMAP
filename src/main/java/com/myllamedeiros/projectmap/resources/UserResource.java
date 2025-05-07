package com.myllamedeiros.projectmap.resources;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.dto.CommunityDTO;
import com.myllamedeiros.projectmap.dto.UserDTO;
import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.enums.Curso;
import com.myllamedeiros.projectmap.services.UserService;
import com.myllamedeiros.projectmap.util.ApresentarUsersECommunities;
import com.myllamedeiros.projectmap.util.AtualizadorDeUsersECommunity;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@Autowired
	private AtualizadorDeUsersECommunity atualizadorDeUsersECommunity;
	
	@Autowired
	private ApresentarUsersECommunities apresentarUsersECommunities;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findByIdComplete(@PathVariable String id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping(value = "/{id}/dto")
	public ResponseEntity<UserDTO> findByIdDTO(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@GetMapping(value = "/{id}/communities")
	public ResponseEntity<Set<String>> findCommunitiesIds(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getCommunitiesIds());
	}
	
	@GetMapping(value = "/{id}/communities/dto")
	public ResponseEntity<List<CommunityDTO>> findCommunitiesIdsDto(@PathVariable String id) {
		List<CommunityDTO> list = apresentarUsersECommunities.retornandoComunidades(id);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> insert(@RequestParam("matricula") String matricula, @RequestParam("nome") String nome,
			@RequestParam("nomeDeUsuario") String nomeDeUsuario, @RequestParam("email") String email,
			@RequestParam("campus") Campus campus, @RequestParam("curso") Curso curso,
			@RequestParam("dataNascimento") Date dataNascimento, @RequestParam("senha") String senha,
			@RequestParam("descricao") String descricao, @RequestParam("imagem") MultipartFile imagem) {

		if (matricula.isBlank() || nome.isBlank() || nomeDeUsuario.isBlank() || email.isBlank() || campus == null
				|| curso == null || dataNascimento == null || senha.isBlank() || descricao.isBlank()
				|| imagem == null) {
			return ResponseEntity.badRequest().build();
		}

		try {
			User obj = service.insert(
					new User(matricula, nome, nomeDeUsuario, email, campus, curso, dataNascimento, senha, descricao),
					imagem);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getMatricula())
					.toUri();

			return ResponseEntity.created(uri).build();
		} catch (IOException e) {
			System.out.print("Erro ao salvar user: " + e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}/imagem")
	public ResponseEntity<byte[]> getImagem(@PathVariable String id) {
		User user = service.findById(id);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"imagem.jpg\"").body(user.getImagem());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody User newUser, @PathVariable String id) {
		newUser = service.update(newUser, id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("{matricula}/community/{id}")
	public ResponseEntity<Void> updateListaComunidades(@PathVariable String matricula, @PathVariable String id){
		atualizadorDeUsersECommunity.atualizadorDeUsersECommunity(matricula, id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/denuncias")
	public ResponseEntity<Void> denuncias(@PathVariable String id) {
		User user = service.findById(id);
		
		user.setDenuncias();
		if(user.getDenuncias() >= 5) {
			delete(id);
		} else {
			service.updates(user);
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
