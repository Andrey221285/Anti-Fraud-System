package antifraud.security;

import antifraud.persistence.dao.UserRepository;
import antifraud.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameIgnoreCase(username);
        if (user == null){
            throw new UsernameNotFoundException("Not found: " + username);
        }


        return new UserDetailsImpl(user);
    }
}
