package org.personal.details.repository;

import org.personal.details.domain.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {

    Optional<PersonalDetails> findOptionalPersonalDetailsByCustomerRef(String customerRef);

}
