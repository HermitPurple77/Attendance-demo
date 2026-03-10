package landing.service;

import landing.model.IpAddress;
import landing.repository.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class PingScheduler {

    @Autowired
    private PingService pingService;

    @Autowired
    private IpAddressRepository ipAddressRepository;

    // Ping all addresses every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void pingAllAddresses() {
        pingService.pingAll();
    }
}