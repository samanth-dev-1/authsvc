package com.openmarket.auth.authsvc.app.auth.service;

import com.openmarket.auth.authsvc.app.auth.models.Address;
import com.openmarket.auth.authsvc.app.auth.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Address createOrGetAddress(Address address) {
        // Check for existing address
        Optional<Address> existingAddress = addressRepository.findDuplicateAddress(address);
        if (existingAddress.isPresent()) {
            return existingAddress.get();
        }

        // If no duplicate found, create new address
        return addressRepository.save(address);
    }

    public Optional<Address> getAddress(Integer id) {
        return addressRepository.findById(id);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }
}