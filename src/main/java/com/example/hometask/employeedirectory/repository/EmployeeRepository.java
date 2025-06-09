package com.example.hometask.employeedirectory.repository;

import com.example.hometask.employeedirectory.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    // Метод для поиска сотрудников по опыту работы
    List<EmployeeEntity> findByWorkExperience(Integer experience);
    // Метод для поиска сотрудников по имени, содержащему указанную строку
    List<EmployeeEntity> findByEmployeeNameContaining(String name);
}
