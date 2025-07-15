package MovieBackend.MovieBackend.service;

import MovieBackend.MovieBackend.dto.LoginRequest;
import MovieBackend.MovieBackend.dto.LoginResponse;
import MovieBackend.MovieBackend.dto.PasswordUpdateRequest;
import MovieBackend.MovieBackend.dto.UserDTo;
import MovieBackend.MovieBackend.model.Users;
import MovieBackend.MovieBackend.repository.UserRepository;
import MovieBackend.MovieBackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public UserDTo register(Users users){
        String email=users.getEmail();
        Users user=repository.findByEmail(email);
        if(user!=null){
            throw  new RuntimeException("Email Already Exist");
        }
            users.setPassword(encoder.encode(users.getPassword()));
            Users users1=repository.save(users);

            return new UserDTo(users1.getId(), users1.getFullName(), users1.getEmail(), users1.getPreferences());

    }
    public LoginResponse login(LoginRequest request){
        Users users=repository.findByEmail(request.getEmail());

        if( users==null || !encoder.matches(request.getPassword(), users.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }

            return new LoginResponse(util.generateToken(users.getEmail()));
    }

    public UserDTo getLoggedInUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        if(authentication==null){
            throw new RuntimeException("UnAuthenticated");
        }

            String email=authentication.getName();
            Users users=repository.findByEmail(email);
            return new UserDTo(users.getId(), users.getFullName(), users.getEmail(), users.getPreferences());

    }
    public UserDTo deleteUser(int id){
        Users users=repository.findById(id).orElseThrow(()->new RuntimeException("Could not find User"));
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Not Authorized");
        }
        else {
           String email=authentication.getName();
           Users user=repository.findByEmail(email);
           if (user.getId()!=id){
               throw new RuntimeException("Not Authorized");
           }
           repository.delete(user);
           return new UserDTo(user.getId(), user.getFullName(), user.getEmail(), user.getPreferences());

        }
    }

    public String changePassword(PasswordUpdateRequest request){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Not Authorized");
        } else {
            String email=authentication.getName();
            Users users=repository.findByEmail(email);

            if(!encoder.matches(request.getOldPassword(), users.getPassword())){
                throw new RuntimeException("Old Password is invalid");
            }

            users.setPassword(encoder.encode(request.getNewPassword()));

            repository.save(users);
            return "Password Updated Successfully";




        }
    }
}
