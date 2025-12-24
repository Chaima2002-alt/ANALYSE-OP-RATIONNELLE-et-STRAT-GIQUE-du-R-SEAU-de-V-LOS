package com.example.iot.repository;

import com.example.iot.model.IoTDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IoTDeviceRepository extends JpaRepository<IoTDevice, Long> {
    // Cette méthode permet au DataSeeder de vérifier si le capteur existe déjà
    Optional<IoTDevice> findByName(String name);
}