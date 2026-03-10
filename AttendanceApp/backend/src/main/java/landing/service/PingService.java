package landing.service;

import landing.model.IpAddress;
import landing.repository.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PingService {

    @Autowired
    private IpAddressRepository ipAddressRepository;

    public void pingAll() {
        List<IpAddress> addresses = ipAddressRepository.findAll();
        for (IpAddress address : addresses) {
            // if (address.isEnable()) {   ← currently commented out
            //     pingAddress(address);
            // }
            pingAddress(address);
        }
    }

    private void pingAddress(IpAddress address) {
        try {
            InetAddress inetAddress = InetAddress.getByName(address.getAddress().trim());
            LocalDateTime now = LocalDateTime.now();

            long startTime = System.currentTimeMillis();
            boolean reachable = inetAddress.isReachable(5000); // 5 second timeout
            long endTime = System.currentTimeMillis();

            address.setLastChecked(now);
            address.setResponseTime(endTime - startTime);
            address.setStatus(reachable ? "UP" : "DOWN");

            ipAddressRepository.save(address);
        } catch (IOException e) {
            address.setLastChecked(LocalDateTime.now());
            address.setStatus("ERROR");
            address.setResponseTime(null);
            ipAddressRepository.save(address);
        }
    }
}