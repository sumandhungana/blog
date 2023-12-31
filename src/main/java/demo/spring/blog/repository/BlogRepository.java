package demo.spring.blog.repository;


import demo.spring.blog.model.BlogEntity;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author suman dhungana
 */

@EnableJdbcRepositories
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    List<BlogRepository> findByNameContaining(String name);
}
