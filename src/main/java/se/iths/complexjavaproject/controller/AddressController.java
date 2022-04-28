package se.iths.complexjavaproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Address;
import se.iths.complexjavaproject.service.AddressService;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("address")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService){this.addressService = addressService;}


    @PostMapping("")
    public ResponseEntity<Address> createAddress(@RequestBody Address address){
        Address createdAddress = addressService.createAddress(address);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Address>> getAddressById(@PathVariable Long id){
        Optional<Address> foundAddress = addressService.getAddressById(id);
        return new ResponseEntity<>(foundAddress, HttpStatus.FOUND);

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id) throws FileNotFoundException {
        Address foundAddress = addressService.getAddressById(id).orElseThrow(FileNotFoundException::new);
        addressService.deleteAddress(foundAddress.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<Address>> findAllUsers() {
        Iterable<Address> allAddresses = addressService.getAllAddresses();
        return new ResponseEntity<>(allAddresses, HttpStatus.OK);
    }
}
