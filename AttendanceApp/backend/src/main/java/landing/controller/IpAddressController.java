package landing.controller;

import landing.model.IpAddress;
import landing.repository.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ips")
@CrossOrigin(origins = "*")
public class IpAddressController {

    @Autowired
    private IpAddressRepository ipAddressRepository;

    @GetMapping
    public List<IpAddress> getAllIpAddresses() {
        return ipAddressRepository.findAll();
    }
}