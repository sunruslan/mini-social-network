package com.cis.minisocialnetwork.Security;

import com.cis.minisocialnetwork.Model.User;
import com.cis.minisocialnetwork.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        final User user = userRepository.findByNickname(nickname);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + nickname + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(nickname)//
                .password("Default")//
                .authorities(user.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}