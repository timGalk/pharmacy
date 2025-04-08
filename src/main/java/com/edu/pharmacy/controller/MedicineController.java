package com.edu.pharmacy.controller;

import com.edu.pharmacy.service.DTO.MedicineCreateDTO;
import com.edu.pharmacy.service.DTO.MedicineDTO;
import com.edu.pharmacy.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {
    public final MedicineService medicineService;

    @GetMapping(value = "/{id}")
    public MedicineDTO getMedicine(@PathVariable Long id) {
        return medicineService.getMedicine(id);

    }

    @PostMapping
    public MedicineDTO addNewMedicine(@RequestBody MedicineCreateDTO medicine) {
        return medicineService.createMedicine(medicine);
    }

    @GetMapping
    public List<MedicineDTO> getAll() {
        return medicineService.getAllMedicines();

    }

}
