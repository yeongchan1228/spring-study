package spring1.spring1Study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring1.spring1Study.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemeberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
