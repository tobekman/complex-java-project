package se.iths.complexjavaproject.service;

import se.iths.complexjavaproject.entity.Address;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.repository.AddressRepository;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository, AddressRepository adressRepository)
    {this.addressRepository = addressRepository;}

    public Address createAddress(Address address){
        return addressRepository.save(address);
    }
}
