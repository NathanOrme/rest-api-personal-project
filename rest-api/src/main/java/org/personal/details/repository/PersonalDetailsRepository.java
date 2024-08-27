package org.personal.details.repository;

import org.personal.details.domain.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link PersonalDetails} entities.
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * and custom queries for {@link PersonalDetails} entities.
 */
@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {

    /**
     * Finds a {@link PersonalDetails} entity by its customer reference.
     *
     * @param customerRef The customer reference to search for.
     * @return An {@link Optional} containing the found {@link PersonalDetails} if present,
     * or an empty {@link Optional} if no entity is found with the given customer reference.
     */
    Optional<PersonalDetails> findOptionalPersonalDetailsByCustomerRef(String customerRef);

}