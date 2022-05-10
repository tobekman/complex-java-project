package se.iths.complexjavaproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Address;
import se.iths.complexjavaproject.exception.UserNotFoundException;
import se.iths.complexjavaproject.service.AddressService;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService){this.addressService = addressService;}


    @PostMapping("")
    public ResponseEntity<Address> createAddress(@RequestBody Address address){
        Address createdAddress = addressService.createAddress(address);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) throws FileNotFoundException {
        Address foundAddress = addressService.getAddressById(id).orElseThrow(FileNotFoundException::new);
        return new ResponseEntity<>(foundAddress, HttpStatus.FOUND);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws FileNotFoundException {
        Address foundAddress = addressService.getAddressById(id).orElseThrow(FileNotFoundException::new);
        addressService.deleteAddress(foundAddress.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Iterable<Address>> findAll() {
        Iterable<Address> allAddresses = addressService.getAllAddresses();
        return new ResponseEntity<>(allAddresses, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Address> update(@RequestBody Address address){
        if(addressService.getAddressById(address.getId()).isEmpty()){
            throw new UserNotFoundException("address with id does not exist - please create new address");
        }
        else{
            addressService.createAddress(address);
            return new ResponseEntity<>(address,HttpStatus.OK);
        }

    }
}
