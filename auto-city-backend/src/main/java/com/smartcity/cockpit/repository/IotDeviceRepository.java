package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.IotDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IotDeviceRepository extends JpaRepository<IotDevice, Long> {
    Optional<IotDevice> findByDeviceId(String deviceId);
    List<IotDevice> findByStatus(String status);
    List<IotDevice> findByType(String type);
}