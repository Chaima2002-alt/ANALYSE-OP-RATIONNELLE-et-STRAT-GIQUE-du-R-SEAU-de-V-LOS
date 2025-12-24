package com.example.iot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "devices")
public class IoTDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 50)
    private String name;

    private String type;
    
    private String location;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @OneToMany(mappedBy = "device", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Telemetry> telemetries;
    
    public enum DeviceStatus {
        ON, OFF, MAINTENANCE
    }

    // Constructors
    public IoTDevice() {}

    public IoTDevice(String name, String type, String location, DeviceStatus status) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public DeviceStatus getStatus() { return status; }
    public void setStatus(DeviceStatus status) { this.status = status; }

    public List<Telemetry> getTelemetries() { return telemetries; }
    public void setTelemetries(List<Telemetry> telemetries) { this.telemetries = telemetries; }
}
