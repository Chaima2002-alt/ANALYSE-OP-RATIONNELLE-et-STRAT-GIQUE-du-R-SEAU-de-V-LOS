package com.example.iot.controller.api;

import com.example.iot.model.IoTDevice;
import com.example.iot.model.Telemetry;
import com.example.iot.repository.IoTDeviceRepository;
import com.example.iot.repository.TelemetryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/api/devices")
@Tag(name = "IoT Devices", description = "Gestion des objets connectés et télémétries")
public class DeviceController {

    @Autowired
    private IoTDeviceRepository deviceRepository;

    @Autowired
    private TelemetryRepository telemetryRepository;

    // --- SECTION VUES (HTML / THYMELEAF) ---

    @Operation(summary = "Afficher le Dashboard principal")
    @GetMapping("/index")
    public String showDashboard(Model model) {
        model.addAttribute("devices", deviceRepository.findAll());
        return "index"; 
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        IoTDevice device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device ID " + id + " non trouvé"));
        
        model.addAttribute("device", device);
        model.addAttribute("telemetries", device.getTelemetries()); 
        return "details"; 
    }
    // --- SECTION CRUD (AJOUT / MODIF / SUPPRESSION) ---

    @Operation(summary = "Afficher le formulaire d'ajout")
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("device", new IoTDevice());
        return "device-form"; 
    }

    @Operation(summary = "Enregistrer ou modifier un capteur")
    @PostMapping("/save")
    public String saveDevice(@Valid @ModelAttribute("device") IoTDevice device, BindingResult result) {
        if (result.hasErrors()) {
            return "device-form"; // Reste sur le formulaire si erreur
        }
        deviceRepository.save(device);
        return "redirect:/api/devices/index";
    }
    @Operation(summary = "Afficher le formulaire de modification")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        IoTDevice device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device non trouvé"));
        model.addAttribute("device", device);
        return "device-form";
    }

    @Operation(summary = "Supprimer un capteur")
    @GetMapping("/delete/{id}")
    public String deleteDevice(@PathVariable Long id) {
        deviceRepository.deleteById(id);
        // CORRECT : On redirige vers l'URL du dashboard
        return "redirect:/api/devices/index";
    }

    // --- SECTION API (JSON) ---

    @Operation(summary = "Lister tous les devices (JSON)")
    @GetMapping
    @ResponseBody 
    public List<IoTDevice> getAllDevices() {
        return deviceRepository.findAll();
    }
}