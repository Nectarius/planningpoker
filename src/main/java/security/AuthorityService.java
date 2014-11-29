package security;



import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


/**
 *
 */
@Service("authorityService")
public class AuthorityService implements UserDetailsService {

   /* @Autowired
    private AccountRepository accountRepository;*/

    /**
     * @param name n
     * @return user details
     */
    @Override
    public UserDetails loadUserByUsername(String name) {


        List<SimpleGrantedAuthority> roleList = new LinkedList<SimpleGrantedAuthority>();

        roleList.add(new SimpleGrantedAuthority("ROLE_" + "GUEST"));
        //if (account == null) {
        //    throw new UsernameNotFoundException("Unknown user (" + name + ")!");
        //} // if
        //for (Privilege privilege : account.getPrivilegeList()){
        //    String role = privilege.getName().toUpperCase();
        //    roleList.add(new SimpleGrantedAuthority("ROLE_" + role));
        //} // for
        return new org.springframework.security.core.userdetails.User("admin",
                "admin", true, true, true, true, roleList);



    }

}
