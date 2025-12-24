package com.example.iot.service;

import com.example.iot.model.IoTDevice;
import com.example.iot.model.Telemetry;
import com.example.iot.repository.DeviceRepository;
import com.example.iot.repository.TelemetryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class IoTService {

    private final DeviceRepository deviceRepository;
    private final TelemetryRepository telemetryRepository;
    private final Random random = new Random();

    public IoTService(DeviceRepository deviceRepository, TelemetryRepository telemetryRepository) {
        this.deviceRepository = deviceRepository;
        this.telemetryRepository = telemetryRepository;
    }

    public List<IoTDevice> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<IoTDevice> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public IoTDevice saveDevice(IoTDevice device) {
        return deviceRepository.save(device);
    }

    public Telemetry addTelemetry(Long deviceId, Double value, String unit) {
        IoTDevice device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        Telemetry telemetry = new Telemetry(value, unit, LocalDateTime.now(), device);
        return telemetryRepository.save(telemetry);
    }
    
    public List<Telemetry> getTelemetryForDevice(Long deviceId) {
        return telemetryRepository.findByDeviceIdOrderByTimestampDesc(deviceId);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    @Transactional
    public void generateDummyData() {
        if (deviceRepository.count() == 0) {
            IoTDevice d1 = new IoTDevice("Temp Sensor 1", "SENSOR", "Room 101", IoTDevice.DeviceStatus.ON);
            IoTDevice d2 = new IoTDevice("Light Switch 1", "ACTUATOR", "Hallway", IoTDevice.DeviceStatus.OFF);
            
            deviceRepository.save(d1);
            deviceRepository.save(d2);

            addTelemetry(d1.getId(), 22.5, "Celsius");
            addTelemetry(d1.getId(), 23.0, "Celsius");
        }
    }
}
