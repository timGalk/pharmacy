package com.edu.pharmacy.service;


import com.edu.pharmacy.DTO.medicine.MedicineCreateDTO;
import com.edu.pharmacy.DTO.medicine.MedicineDTO;
import com.edu.pharmacy.DTO.medicine.MedicineUpdateDTO;

import java.util.List;

public interface MedicineService {
    MedicineDTO createMedicine(MedicineCreateDTO medicine);

    MedicineDTO getMedicine(Long id);

    List<MedicineDTO> getAllMedicines();

    void deleteMedicine(Long id);

    MedicineDTO updateMedicine(Long id, MedicineUpdateDTO medicine);
}
