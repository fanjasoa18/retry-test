package com.example.prog4.repository.cnaps;

import com.example.prog4.repository.cnaps.entity.CnapsPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CnapsPhoneRepository extends JpaRepository<CnapsPhone, String> {
    @Query(value = "select * from \"phone\" p where p.value = :value", nativeQuery = true)
    Optional<CnapsPhone> findOneByValue(@Param("value") String value);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM \"phone\" p WHERE p.value = :value)", nativeQuery = true)
    boolean isPhoneAlreadyExist(@Param("value") String value);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM \"phone\" p WHERE p.id = :id AND p.employee_id IS NOT NULL AND p.employee_id != :employee_id)", nativeQuery = true)
    boolean isPhoneAlreadyUsedByOthers(@Param("id") String id, @Param("employee_id") String employeeId);
}
