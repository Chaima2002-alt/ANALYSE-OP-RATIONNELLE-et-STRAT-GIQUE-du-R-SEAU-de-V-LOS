package com.example.iot.repository;

import com.example.iot.model.IoTDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<IoTDevice, Long> {
    List<IoTDevice> findByStatus(IoTDevice.DeviceStatus status);
}
