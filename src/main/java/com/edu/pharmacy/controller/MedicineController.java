package com.edu.pharmacy.controller;

import com.edu.pharmacy.DTO.medicine.MedicineCreateDTO;
import com.edu.pharmacy.DTO.medicine.MedicineDTO;
import com.edu.pharmacy.DTO.medicine.MedicineUpdateDTO;
import com.edu.pharmacy.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
    public MedicineDTO addNewMedicine(@Validated @RequestBody MedicineCreateDTO medicine) {
        return medicineService.createMedicine(medicine);
    }

    @GetMapping
    public List<MedicineDTO> getAll() {
        return medicineService.getAllMedicines();

    }
    @DeleteMapping
    public void deleteMedicine(@RequestParam Long id) {
        medicineService.deleteMedicine(id);
    }

    @PatchMapping("/{id}")
    public MedicineDTO patchMedicine(@PathVariable Long id, @RequestBody MedicineUpdateDTO dto) {
        return medicineService.updateMedicine(id, dto); // or patchMedicine
    }



}
