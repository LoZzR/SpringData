package hibernate.repo;

import hibernate.entities.Person;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import hibernate.repo.IPersonRepo;

import java.util.HashSet;
import java.util.List;
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
        Person p = session().find(Person.class, id);
        return p != null ? Optional.of(p) : Optional.empty();
    }

    @Override
    public Person addPerson(Person person) {

        return this.findById((Long)session().save(person)).orElseThrow(
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
