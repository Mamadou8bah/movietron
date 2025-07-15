package MovieBackend.MovieBackend.service;

import MovieBackend.MovieBackend.dto.LoginRequest;
import MovieBackend.MovieBackend.dto.LoginResponse;
import MovieBackend.MovieBackend.model.Users;
import MovieBackend.MovieBackend.repository.UserRepository;
import MovieBackend.MovieBackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil util;
    public Users register(Users users){
        String email=users.getEmail();
        Users user=repository.findByEmai(email);
        if(user!=null){
            throw  new RuntimeException("Email Already Exist");
        }else
            return repository.save(users);
    }
    public LoginResponse login(LoginRequest request){
        Users users=repository.findByEmai(request.getEmail());

        boolean passwordIsValid=encoder.matches(request.getPassword(), users.getPassword());

        if(users==null &&!passwordIsValid){
            throw new RuntimeException("Invalid Credentials");
        }else
            return new LoginResponse(util.generateToken(users.getEmail()));
    }
}
