package service;

import entity.Address;
import org.springframework.stereotype.Service;
import repository.AddressRepository;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository, AddressRepository adressRepository)
    {this.addressRepository = addressRepository;}

    public Address createAddress(Address address){
        return addressRepository.save(address);
    }
}
