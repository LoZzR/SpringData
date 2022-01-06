package repo;

import entities.Detective;

import java.util.Optional;
import java.util.Set;

public interface IDetectiveRepo {

    Optional<Detective> findById(Long id);

    Optional<Detective> findByBadgeNumber(String badgeNumber);

    default Optional<Detective> findByIdWithPersonDetails(Long id){
        return Optional.empty();
    }
}
