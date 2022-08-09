package demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<Demo, Long> {
	List<Demo> findByIdGreaterThanEqualOrderByIdAsc(int minId);
}
