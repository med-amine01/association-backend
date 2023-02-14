package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.JwtRequest;
import de.tekup.associationspringboot.entity.JwtResponse;
import de.tekup.associationspringboot.entity.User;
import de.tekup.associationspringboot.repository.UserRepository;
import de.tekup.associationspringboot.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Component
@Service

public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    //this will take jwt request and it will process it to get jwt response
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        //check for authentication to provide jwt Token
        authenticate(userName,userPassword);
        final UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        User user = userRepository.findById(userName).get();
        return new JwtResponse(user, newGeneratedToken);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).get();

        if(user != null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUserEmail(),
                    user.getUserPassword(),
                    getAuthorities(user)
            );
        }
        else{
            throw new UsernameNotFoundException("User is not valid");
        }
    }

    private Set getAuthorities(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            //another way to do this you can add new SimpleGrantedAuthority("ROLE_" + role.getRoleName())
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return authorities;
    }
    private void authenticate(String userName, String userPassword) throws Exception{
        //with this approach will have to handle two exceptions :
        //1 - the user is disabled
        //2 - the user gave bad credentials
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        }
        catch (DisabledException e){
            throw new Exception("User is disabled");
        }
        catch (BadCredentialsException e){
            throw new Exception("Bad Credentials from user");
        }
    }
}
