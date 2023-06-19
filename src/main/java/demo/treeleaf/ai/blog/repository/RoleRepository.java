package demo.treeleaf.ai.blog.repository;


import demo.treeleaf.ai.blog.model.ERole;
import demo.treeleaf.ai.blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
