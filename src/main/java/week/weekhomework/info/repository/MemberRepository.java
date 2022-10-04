package week.weekhomework.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week.weekhomework.info.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String userEmail);
}
