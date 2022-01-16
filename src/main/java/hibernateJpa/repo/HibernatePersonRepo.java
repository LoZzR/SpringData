package hibernateJpa.repo;

import hibernateJpa.entities.Person;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
//@Transactional
public class HibernatePersonRepo implements IPersonRepo {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return the transactional session
     */
    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Optional<Person> findById(Long id) {
        if(true) throw new RuntimeException();
        Person p = session().find(Person.class, id);
        return p != null ? Optional.of(p) : Optional.empty();
    }

    @Override
    public Person addPerson(Person person) {
        Long id = (Long)session().save(person);
        findAll();
        return this.findById(id).orElseThrow(
                ()->new RuntimeException("Error while adding person !")
        );
    }

    @Override
    public Long getNextId() {
        return null;
    }

    @Override
    public Set<Person> findAll() {
        Query<Person> query = session().createQuery("from Person");
        Set<Person> persons = new HashSet<>(session().createQuery("from Person").list());
        return persons;
    }
}
