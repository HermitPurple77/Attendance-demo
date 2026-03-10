package landing.service;

import landing.model.IpAddress;
import landing.repository.IpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PingWebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private IpAddressRepository ipAddressRepository;

    public void sendUpdatedStatus() {
        List<IpAddress> addresses = ipAddressRepository.findAll();
        messagingTemplate.convertAndSend("/topic/status", addresses);
    }
}