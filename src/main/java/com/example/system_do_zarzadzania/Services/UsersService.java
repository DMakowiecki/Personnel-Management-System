package com.example.system_do_zarzadzania.Services;
import com.example.system_do_zarzadzania.Entity.Authorities;
import com.example.system_do_zarzadzania.Entity.Users;
import com.example.system_do_zarzadzania.Repostitory.AuthoritiesRepository;
import com.example.system_do_zarzadzania.Repostitory.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users saveUser(String username, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        Users savedUser = usersRepository.save(user);
        Authorities authority = new Authorities();
        authority.setUsername(username);
        authority.setAuthority("ROLE_USER");
        authoritiesRepository.save(authority);
        return savedUser;
    }
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
