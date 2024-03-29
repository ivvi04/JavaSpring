package ru.lakeevda.authserver.config.custom;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public final class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {
	private final UserRepository userRepository = new UserRepository();

	@Override
	public void accept(OAuth2User user) {
		if (this.userRepository.findByName(user.getName()) == null) this.userRepository.save(user);
	}

	static class UserRepository {
		private final Map<String, OAuth2User> userCache = new ConcurrentHashMap<>();
		public OAuth2User findByName(String name) {
			return this.userCache.get(name);
		}
		public void save(OAuth2User oauth2User) {
			this.userCache.put(oauth2User.getName(), oauth2User);
		}

	}

}
