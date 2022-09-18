package week.weekhomework.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week.weekhomework.info.domain.info;

public interface InfoRepository extends JpaRepository<info, Long> {
}
