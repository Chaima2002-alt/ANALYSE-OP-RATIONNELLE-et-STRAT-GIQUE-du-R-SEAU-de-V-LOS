package com.example.iot.config;

import com.example.iot.model.User;
import com.example.iot.model.IoTDevice;
import com.example.iot.repository.UserRepository;
import com.example.iot.repository.IoTDeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, 
                                      IoTDeviceRepository deviceRepository, 
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Initialisation de l'utilisateur de test
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("password"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("✅ [DataSeeder] Utilisateur créé : admin / password");
            }

            // 2. Initialisation des capteurs par défaut
            if (deviceRepository.count() == 0) {
                // Création du Capteur Salon
                IoTDevice salon = new IoTDevice();
                salon.setName("Capteur Salon");
                salon.setType("TEMP");
                salon.setLocation("Séjour");
                salon.setStatus(IoTDevice.DeviceStatus.ON); 
                
                // Création du Capteur Cuisine
                IoTDevice cuisine = new IoTDevice();
                cuisine.setName("Capteur Cuisine");
                cuisine.setType("HUMIDITY");
                cuisine.setLocation("Cuisine");
                cuisine.setStatus(IoTDevice.DeviceStatus.ON);
                
                // Création du Capteur Chambre
                IoTDevice chambre = new IoTDevice();
                chambre.setName("Capteur Chambre");
                chambre.setType("LIGHT");
                chambre.setLocation("Chambre");
                chambre.setStatus(IoTDevice.DeviceStatus.OFF);

                // Sauvegarde de tous les capteurs en une fois
                deviceRepository.saveAll(Arrays.asList(salon, cuisine, chambre));
                System.out.println("✅ [DataSeeder] 3 capteurs IoT injectés dans la base de données");
            } else {
                System.out.println("ℹ️ [DataSeeder] Des capteurs existent déjà, injection ignorée.");
            }
        };
    }
}