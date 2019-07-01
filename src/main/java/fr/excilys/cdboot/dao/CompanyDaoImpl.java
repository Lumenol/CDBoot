package fr.excilys.cdboot.dao;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fr.excilys.cdboot.model.Company;
import fr.excilys.cdboot.model.QCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private static final QCompany Q_COMPANY = QCompany.company;
    private JPAQueryFactory queryFactory;
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(entityManager);
    }

    private OrderSpecifier toOrder(Sort.Order order) {

    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        pageable.getSort().stream().map(this::toOrder)
        return queryFactory.selectFrom(Q_COMPANY).orderBy()
    }

    @Override
    public Optional<Company> find(long id) {
        return Optional.ofNullable(entityManager.find(Company.class, id));
    }

    @Override
    public void save(Company company) {
        if (Objects.nonNull(company.getId())) {
            entityManager.merge(company);
        } else {
            entityManager.persist(company);
        }
    }

    @Override
    public void delete(long id) {
        queryFactory.delete(Q_COMPANY).where(Q_COMPANY.id.eq(id)).execute();
    }
}
