package com.upspoon.authorization.service;

import com.upspoon.authorization.model.UserDetail;
import com.upspoon.authorization.repository.UserDetailRepository;
import com.upspoon.common.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author burak.yesildal
 */

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements AuthenticationProvider {
    private final UserDetailRepository userDetailRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<UserDetail> userDetail = userDetailRepository.findByEmail(authentication.getName());
        UserDetail user = userDetail.orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials().toString(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserDetail> userDetail = userDetailRepository.findByEmail(username);
//        return userDetail.orElseThrow(UserNotFoundException::new);
//
//    }
}
