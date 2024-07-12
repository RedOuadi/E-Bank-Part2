package bank.service;

import bank.config.JwtService;
import bank.dto.AuthRequestDTO;
import bank.dto.JwtResponseDTO;
import bank.dto.SignUpRequestDto;
import bank.model.User;
import bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceAuth implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Lazy
    @Autowired
    private JwtService jwtService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        System.out.println("useeeeeeeeeeeeeer "+user);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public JwtResponseDTO login(AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getMail(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return JwtResponseDTO.builder().accessToken(jwtService.GenerateToken(authRequestDTO.getMail())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    public JwtResponseDTO signUp(SignUpRequestDto userRequest) {
        if(userRequest.getMail() == null){
            throw new RuntimeException("Parameter username is not found in request..!!");
        } else if(userRequest.getPassword() == null){
            throw new RuntimeException("Parameter password is not found in request..!!");
        }
        User user = new User();
        user.setEmail(userRequest.getMail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return JwtResponseDTO.builder().accessToken(jwtService.GenerateToken(userRepository.save(user).getUsername())).build();
    }

}
