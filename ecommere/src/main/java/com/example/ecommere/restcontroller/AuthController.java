package com.example.ecommere.restcontroller;

import com.example.ecommere.constant.ErrorCode;
import com.example.ecommere.constant.SuccessCode;
import com.example.ecommere.dto.ResponseDTO;
import com.example.ecommere.enums.ERole;
import com.example.ecommere.model.Role;
import com.example.ecommere.model.User;
import com.example.ecommere.payload.request.LoginRequest;
import com.example.ecommere.payload.request.SignupRequest;
import com.example.ecommere.payload.response.JwtResponse;
import com.example.ecommere.payload.response.MessageResponse;
import com.example.ecommere.repository.RoleRepository;
import com.example.ecommere.repository.UserRepository;
import com.example.ecommere.security.jwt.JwtUtils;
import com.example.ecommere.security.service.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final private AuthenticationManager authenticationManager;

    final private UserRepository userRepository;

    final private RoleRepository roleRepository;

    final private PasswordEncoder encoder;

    final private JwtUtils jwtUtils;

    public AuthController (AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        ResponseDTO response = new ResponseDTO();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // if go there, the user/password is correct
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate jwt to return to client
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtResponse jwtResponse = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
        response.setData(jwtResponse);
        response.setSuccessCode(SuccessCode.USER_LOGIN_SUCCESS);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        ResponseDTO response = new ResponseDTO();
        SignupRequest signup = signUpRequest;
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            response.setErrorCode(ErrorCode.USERNAME_NOT_AVAILABLE);
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            response.setErrorCode(ErrorCode.EMAIL_NOT_AVAILABLE);
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }

        // Create new user's account
        User user = new User(signup.getUsername(),
                signup.getEmail(),
                encoder.encode(signup.getPassword()));

        Set<String> strRoles = signup.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        response.setData(signup);
        response.setSuccessCode(SuccessCode.USER_SIGNUP_SUCCESS);

        return ResponseEntity.ok().body(response);
    }
}