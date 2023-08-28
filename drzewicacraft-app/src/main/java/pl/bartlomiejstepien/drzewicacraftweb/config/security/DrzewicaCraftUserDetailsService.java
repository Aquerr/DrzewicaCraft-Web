package pl.bartlomiejstepien.drzewicacraftweb.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DrzewicaCraftUserDetailsService implements UserDetailsService
{
//    private final UserCredentialsRepository userCredentialsRepository;
//    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public AuthenticatedUser loadUserByUsername(String username) throws UsernameNotFoundException
    {
//        UserCredentials.UsernameOrEmail usernameOrEmail = new UserCredentials.UsernameOrEmail(username);
//
//        UserCredentialsEntity userCredentialsEntity = null;
//        if (usernameOrEmail.isEmail())
//        {
//            userCredentialsEntity = userCredentialsRepository.findByEmail(usernameOrEmail.getValue());
//        }
//        else
//        {
//            userCredentialsEntity = userCredentialsRepository.findByUsername(usernameOrEmail.getValue());
//        }
//
//        if (userCredentialsEntity == null)
//        {
//            throw new UsernameNotFoundException("User does not exist!");
//        }
//        if (userCredentialsEntity.isLocked())
//        {
//            throw new UserLockedException();
//        }
//        if (!userCredentialsEntity.isActivated())
//        {
//            throw new UserNotActivatedException();
//        }
//
//        UserProfileEntity userProfileEntity = profileRepository.findByCredentialsId(userCredentialsEntity.getId());
//        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        return new AuthenticatedUser(userCredentialsEntity.getId(),
//                userCredentialsEntity.getUsername(),
//                userCredentialsEntity.getPassword(),
//                userProfileEntity.getId(),
//                getClientIp(httpServletRequest),
//                userCredentialsEntity.getAuthorities().stream()
//                        .map(SimpleGrantedAuthority::new)
//                        .toList());

        return null;
    }

//    private String getClientIp(HttpServletRequest request)
//    {
//        String remoteAddr = "";
//
//        if (request != null)
//        {
//            remoteAddr = request.getHeader("X-FORWARDED-FOR");
//            if (remoteAddr == null || "".equals(remoteAddr))
//            {
//                remoteAddr = request.getRemoteAddr();
//            }
//        }
//        return remoteAddr;
//    }
}