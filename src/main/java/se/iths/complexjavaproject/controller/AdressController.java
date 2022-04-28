package se.iths.complexjavaproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Address;
import se.iths.complexjavaproject.service.AddressService;

import java.util.Optional;

@RestController
@RequestMapping("address")
public class AdressController {
    private final AddressService addressService;
    public AdressController(AddressService addressService){this.addressService = addressService;}


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
}
