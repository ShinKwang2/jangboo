package cafe.jangboo;

import cafe.jangboo.member.domain.Company;
import cafe.jangboo.member.domain.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Component
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @RequiredArgsConstructor
    @Component
    @Transactional
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            MemberEntity memberA = MemberEntity.builder()
                    .name("이신광")
                    .company(new Company("KPC", "경영지원"))
                    .phone("01051391314")
                    .balance(new Money(50000))
                    .build();
            em.persist(memberA);

            MemberEntity memberB = MemberEntity.builder()
                    .name("김솔이")
                    .company(new Company("413커피"))
                    .phone("01089869932")
                    .balance(new Money(100000))
                    .build();
            em.persist(memberB);
        }


    }
}
