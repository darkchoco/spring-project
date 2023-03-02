package darkchoco.reactspring.service;

import java.util.Optional;

import darkchoco.reactspring.domain.SiteUser;
import darkchoco.reactspring.domain.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private SiteUserRepository siteUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<SiteUser> user = siteUserRepository.findByUsername(username);

		if (user.isPresent()) {
			SiteUser currentSiteUser = user.get();

            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(currentSiteUser.getPassword())
                    .roles(currentSiteUser.getRole())
                    .build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}
