package com.myllamedeiros.projectmap.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.dto.CommunityDTO;
import com.myllamedeiros.projectmap.dto.UserDTO;
import com.myllamedeiros.projectmap.services.CommunityService;
import com.myllamedeiros.projectmap.services.UserService;

@Component
public class ApresentarUsersECommunities {

	@Autowired
	private CommunityService commService;
	
	@Autowired
	private UserService userService;
	
	public List<CommunityDTO> retornandoComunidades(String matricula){
		User user = userService.findById(matricula);
		List<CommunityDTO> communities = new ArrayList<>();
		
		for(String mat: user.getCommunitiesIds()) {
			Community comm = commService.findById(mat);
			CommunityDTO commDTO = new CommunityDTO(comm);
			communities.add(commDTO);
		}
		
		return communities;
	}
	
	public List<UserDTO> retornandoUsers(String id){
		Community comm = commService.findById(id);
		List<UserDTO> users = new ArrayList<>();
		
		for(String id_com: comm.getUsersIds()) {
			User user = userService.findById(id_com);
			UserDTO userDTO = new UserDTO(user);
			users.add(userDTO);
		}
		
		return users;
	}
}
