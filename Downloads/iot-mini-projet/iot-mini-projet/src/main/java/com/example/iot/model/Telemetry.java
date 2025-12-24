package com.example.iot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetries")
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_value")
    private Double value;
    
    private String unit;

    @Column(name = "telemetry_timestamp")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private IoTDevice device;

    // Constructors
    public Telemetry() {}

    public Telemetry(Double value, String unit, LocalDateTime timestamp, IoTDevice device) {
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
        this.device = device;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public IoTDevice getDevice() { return device; }
    public void setDevice(IoTDevice device) { this.device = device; }
}
