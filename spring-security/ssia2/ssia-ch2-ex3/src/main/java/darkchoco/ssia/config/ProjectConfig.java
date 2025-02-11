package darkchoco.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());  // App uses HTTP Basic authentication

        http.authorizeHttpRequests(
//            c -> c.anyRequest().authenticated()  // 모든 요청에 인증이 필요하다
                c -> c.anyRequest().permitAll()
        );

        // 1. 새로운 유저 생성
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")  // 아무 문자열이나 일단 지정
                .build();

        // 2. UserDetailsService에서 관리하도록 유저 추가
        var userDetailsService = new InMemoryUserDetailsManager(user);

        // 3. UserDetailsService 구성
        http.userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
