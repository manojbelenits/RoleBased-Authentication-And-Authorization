package org.belen.rolebasedaccess.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.belen.rolebasedaccess.Entity.UserEntity;
import org.belen.rolebasedaccess.enums.Role;
import org.belen.rolebasedaccess.repo.UserEntityRepo;
import org.belen.rolebasedaccess.reqDto.LoginRequest;
import org.belen.rolebasedaccess.reqDto.LoginResponse;
import org.belen.rolebasedaccess.reqDto.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServImpl {

    private final UserEntityRepo repo;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final JwtService  jwtService;


    public UserEntity createTheUser(RegisterRequest request){

        if(repo.existsByUsername(request.getUsername())){
            throw new RuntimeException("UserName Alreday Exists");
        }

        UserEntity user = UserEntity.builder()
                            .username(request.getUsername())
                            .password(encoder.encode(request.getPassword()))
                            .role(Role.EMPLOYEE)
                            .build();
       return repo.save(user);

    }

    public LoginResponse loginMethod(LoginRequest request){


        try{
            Authentication authenticate = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            UserPrinciple currentUser =
                    (UserPrinciple) authenticate.getPrincipal();

            String token = jwtService.generateToken(currentUser.getUsername());

            return LoginResponse.builder()
                    .msg("Login SuccessFull")
                    .toekn(token)
                    .build();
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }

}























