package ru.dsoccer1980.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Author;

@Repository
@Transactional(readOnly = true)
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Author author) {
        if (author.getId() != null) {
            em.merge(author);
        } else {
            em.persist(author);
        }
    }

    @Override
    @Transactional
    public void insert(String name) {
        em.persist(new Author(name));
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        em.createQuery("DELETE FROM Author a WHERE a.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Long getIdByName(String name) {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a WHERE a.name=:name", Author.class);
        query.setParameter("name", name);
        List<Author> resultList = query.getResultList();
        if (resultList.size() == 0) {
            return null;
        }
        if (resultList.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1);
        }
        return resultList.get(0).getId();
    }

    @Override
    @Transactional
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Author");
        query.executeUpdate();
    }


    @Override
    public void getCriteria() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Author.class)
            .add(Property.forName("name").eq("Denis"))
            .add(Property.forName("processed").isNull())
            .setProjection(Projections.property("name"));
        SessionFactory sessionFactory=em.getEntityManagerFactory().unwrap(SessionFactory.class);
        HibernateTemplate hibernate = new HibernateTemplate(sessionFactory);
        List<String> byCriteria = (List<String>)hibernate.findByCriteria(detachedCriteria);
        byCriteria.forEach(System.out::println);


    }


}
