package com.julianduru.omarze.persistence;


import com.julianduru.omarze.entities.Email;
import com.julianduru.omarze.entities.EmailStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * created by julian
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {


    Page<Email> findByEmailStatusIn(Collection<EmailStatus> statusCollection, Pageable pageable);


    @Modifying
    @Query(
        "UPDATE #{#entityName} e SET e.emailStatus = :status WHERE e.id IN (:ids)"
    )
    void updateEmailStatusByIDs(
        @Param("ids") Collection<Long> emails,
        @Param("status") EmailStatus emailStatus
    );


    @Modifying
    @Query(
        "UPDATE #{#entityName} e SET e.emailStatus = :status WHERE e.reference IN (:references)"
    )
    void updateEmailStatusByReferences(
        @Param("references") Collection<String> emails,
        @Param("status") EmailStatus emailStatus
    );


}
