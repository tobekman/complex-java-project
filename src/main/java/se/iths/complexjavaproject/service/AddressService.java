package se.iths.complexjavaproject.service;

import se.iths.complexjavaproject.entity.Address;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.repository.AddressRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository, AddressRepository adressRepository)
    {this.addressRepository = addressRepository;}

    public Address createAddress(Address address){
        return addressRepository.save(address);
    }


    public Optional<Address> getAddressById(Long id){
        return addressRepository.findById(id);
    }

    public void deleteAddress(Long id){
        Address result = addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    addressRepository.deleteById(result.getId());
    }

    public Iterable<Address> getAllAddresses(){
        return addressRepository.findAll();
    }


}
