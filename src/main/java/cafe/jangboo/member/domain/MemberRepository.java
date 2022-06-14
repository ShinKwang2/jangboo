package cafe.jangboo.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByName(String name);

    @Query("SELECT m FROM MemberEntity m ORDER BY m.id DESC ")
    List<MemberEntity> findAllDesc();
}
