package repo;

import jdbctemplate.entities.Detective;

import java.util.Optional;

public interface IDetectiveRepo {

    Optional<Detective> findById(Long id);

    Optional<Detective> findByBadgeNumber(String badgeNumber);

    default Optional<Detective> findByIdWithPersonDetails(Long id){
        return Optional.empty();
    }
}
