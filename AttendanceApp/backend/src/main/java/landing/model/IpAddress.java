package landing.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "device", schema = "complaints")
public class IpAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String address;

    private boolean enable = true;

    @Column(name = "last_checked")
    private LocalDateTime lastChecked;

    @Column(name = "status")
    private String status;

    @Column(name = "response_time")
    private Long responseTime;

    // Constructor
    public IpAddress() { super(); }

    // Getters and Setters for all fields...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public boolean isEnable() { return enable; }
    public void setEnable(boolean enable) { this.enable = enable; }
    public LocalDateTime getLastChecked() { return lastChecked; }
    public void setLastChecked(LocalDateTime lastChecked) { this.lastChecked = lastChecked; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getResponseTime() { return responseTime; }
    public void setResponseTime(Long responseTime) { this.responseTime = responseTime; }
}