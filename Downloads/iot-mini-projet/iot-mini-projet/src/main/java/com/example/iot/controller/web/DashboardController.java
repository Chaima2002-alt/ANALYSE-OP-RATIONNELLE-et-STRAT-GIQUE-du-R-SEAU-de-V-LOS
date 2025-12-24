package com.example.iot.controller.web;

import com.example.iot.model.IoTDevice;
import com.example.iot.service.IoTService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    private final IoTService iotService;

    public DashboardController(IoTService iotService) {
        this.iotService = iotService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        List<IoTDevice> devices = iotService.getAllDevices();
        
        model.addAttribute("devices", devices != null ? devices : new ArrayList<>());
        
        return "index";
    }

    @GetMapping("/device/{id}")
    public String deviceDetails(@PathVariable Long id, Model model) {
        iotService.getDeviceById(id).ifPresentOrElse(device -> {
            model.addAttribute("device", device);
            model.addAttribute("telemetries", iotService.getTelemetryForDevice(id));
        }, () -> {
            model.addAttribute("error", "Appareil introuvable");
        });
        return "details";
    }
}