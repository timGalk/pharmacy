package com.edu.pharmacy.service;


import com.edu.pharmacy.service.DTO.MedicineCreateDTO;
import com.edu.pharmacy.service.DTO.MedicineDTO;

import java.util.List;

public interface MedicineService {
    MedicineDTO createMedicine(MedicineCreateDTO medicine);

    MedicineDTO getMedicine(Long id);

    List<MedicineDTO> getAllMedicines();
}
