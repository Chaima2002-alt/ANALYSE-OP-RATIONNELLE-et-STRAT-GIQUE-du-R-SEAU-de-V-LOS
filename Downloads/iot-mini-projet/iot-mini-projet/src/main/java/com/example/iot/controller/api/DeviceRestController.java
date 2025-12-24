package com.example.iot.controller.api;

import com.example.iot.model.IoTDevice;
import com.example.iot.model.Telemetry;
import com.example.iot.repository.IoTDeviceRepository;
import com.example.iot.repository.TelemetryRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/devices")
public class DeviceRestController {

    @Autowired
    private IoTDeviceRepository deviceRepository;

    @Autowired
    private TelemetryRepository telemetryRepository;

    @Operation(summary = "Ajouter une mesure de télémétrie")
    @PostMapping("/{id}/telemetry")
    public Telemetry addTelemetry(@PathVariable Long id, 
                                  @RequestParam Double value, 
                                  @RequestParam String unit) {
        
        IoTDevice device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Capteur non trouvé"));

        Telemetry telemetry = new Telemetry();
        telemetry.setValue(value);
        telemetry.setUnit(unit);
        telemetry.setTimestamp(LocalDateTime.now());
        telemetry.setDevice(device); 

        return telemetryRepository.save(telemetry);
    }
}