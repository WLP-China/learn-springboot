package com.muqing.dto;

import com.muqing.model.Department;
import com.muqing.model.Doctor;

import java.util.List;

/**
 * Create by iFun on 2020/07/22
 */
public class DepartmentDTO extends Department {
    private static final long serialVersionUID = -8381091118423088547L;

    private List<Doctor> doctors;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
