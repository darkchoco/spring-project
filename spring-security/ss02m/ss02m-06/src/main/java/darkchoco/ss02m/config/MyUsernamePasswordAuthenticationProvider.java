package darkchoco.ss02m.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component  // Bean으로 인식하게 해야 Spring Security framework가 내 고유한 AuthenticationProvider를 사용하겠다는 것을 알 수 있다.
@Profile("!prod")
@RequiredArgsConstructor
public class MyUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // Production 환경이 아니라면 Password 체크하지 않도록 한다.
        return new UsernamePasswordAuthenticationToken(username, pwd, userDetails.getAuthorities());
    }

    /**
     * Spring Security는 인증 처리를 위해 여러 AuthenticationProvider를 가질 수 있습니다.
     * 인증 요청이 들어오면, 스프링은 supports() 메서드를 호출하여 어떤 AuthenticationProvider가 해당 인증 요청을 처리할
     * 수 있는지 결정합니다.
     * 이 메서드는 Spring Security의 인증 프로세스에서 중요한 역할을 하며, 특정 타입의 인증 요청만을 처리하도록
     * 필터링하는 역할을 합니다.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
