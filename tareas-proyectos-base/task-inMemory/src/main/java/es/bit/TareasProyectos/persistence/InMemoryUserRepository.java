package es.bit.TareasProyectos.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import es.bit.TareasProyectos.models.User;
import es.bit.TareasProyectos.ports.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryUserRepository implements UserRepository {
	private List<User> catalogData = new ArrayList<>(Arrays.asList(
			new User(1,"ric","r@r.com","rc1"),
			new User(2,"ana","a@a.com","ann2"),
			new User(3,"gab","g@g.com","gab3")
	));

	@Override
	public User findOne(Long id) {
		Optional<User> userOptional = catalogData.stream().filter(user -> user.getUid()==id).findAny();
		return userOptional.orElse(null);
	}

	@Override
	public Collection<User> findAll() {
		return Collections.unmodifiableCollection(catalogData);
	}

	@Override
	public User save(User user) {
		Date date = new Date();
		user.setUid(date.getTime());
		catalogData.add(user);
		return user;
	}

	@Override
	public void delete(User user) {
		catalogData.remove(user);
	}
}
