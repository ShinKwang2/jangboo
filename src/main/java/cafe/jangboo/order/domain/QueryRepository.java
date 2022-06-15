package cafe.jangboo.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class QueryRepository {

    private final EntityManager em;
}
