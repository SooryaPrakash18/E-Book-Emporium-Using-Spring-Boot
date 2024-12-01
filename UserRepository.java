package backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.user;

public interface UserRepository extends JpaRepository<user, String> {
	user findByUsername(String username);
}
