package hibernateJpa.repo;

import hibernateJpa.entities.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class JpaPersonRepo implements IPersonRepo{

    private EntityManager entityManager;

    @PersistenceContext
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Person> findById(Long entityId) {
        Person person = entityManager.find(Person.class, entityId);
        return person == null? Optional.empty() :Optional.of(person);
    }

    @Override
    public Person addPerson(Person person) {
        entityManager.persist(person);
        Set<Person> persons = this.findAll();
        return person;
    }

    @Override
    public Long getNextId() {
        return null;
    }

    @Override
    public Set<Person> findAll() {
        String sql = "from Person p";
        Query query = entityManager.createQuery(sql);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Optional<Person> findByCompleteName(String firstName, String
            lastName) {
        Person person = (Person) entityManager
                .createNamedQuery(Person.FIND_BY_COMPLETE_NAME)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();return person == null? Optional.empty() :Optional.of(person);
    }

    @Override
    public List<Person> findAllByLastName(String lastName) {
        //create the query
        CriteriaBuilder builder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> personRoot = query.from(Person.class);
        ParameterExpression<String> value = builder.parameter(String.class);
        query.select(personRoot).where(
                builder.equal(personRoot.get("lastName"), value));
        // execute the query
        TypedQuery<Person> tquery = entityManager.createQuery(query);
        tquery.setParameter(value,lastName);
        return tquery.getResultList();
    }
}
