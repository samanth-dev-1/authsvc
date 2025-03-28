package com.openmarket.auth.authsvc.app.auth.repository;

import com.openmarket.auth.authsvc.app.auth.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("SELECT a FROM Address a WHERE " +
            "LOWER(a.addressLine1) = LOWER(:#{#address.addressLine1}) AND " +
            "LOWER(COALESCE(a.addressLine2, '')) = LOWER(COALESCE(:#{#address.addressLine2}, '')) AND " +
            "LOWER(a.city) = LOWER(:#{#address.city}) AND " +
            "LOWER(a.state) = LOWER(:#{#address.state}) AND " +
            "LOWER(a.zipcode) = LOWER(:#{#address.zipcode}) AND " +
            "LOWER(a.country) = LOWER(:#{#address.country})")
    Optional<Address> findDuplicateAddress(Address address);
}