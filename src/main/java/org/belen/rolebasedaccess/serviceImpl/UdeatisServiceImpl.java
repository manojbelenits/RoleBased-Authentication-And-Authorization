package org.belen.rolebasedaccess.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.belen.rolebasedaccess.Entity.UserEntity;
import org.belen.rolebasedaccess.repo.UserEntityRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UdeatisServiceImpl implements UserDetailsService {

    private final UserEntityRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {

        UserEntity users = usersRepo.findUserByUname(uname)
                .orElseThrow(() -> new UsernameNotFoundException("Counselor Not Found By That Mail"));

        return new UserPrinciple(users);
    }
}
