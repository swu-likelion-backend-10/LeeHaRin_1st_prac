package week.weekhomework.info.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import week.weekhomework.info.domain.info;

import java.util.List;

public interface InfoRepository extends JpaRepository<info, Long> {
    List<info> findByNameContaining(String keyword);
}
