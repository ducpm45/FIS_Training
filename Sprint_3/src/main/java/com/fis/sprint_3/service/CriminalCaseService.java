package com.fis.sprint_3.service;

import com.fis.sprint_3.model.CriminalCase;

import java.util.List;

public interface CriminalCaseService {
    void create(CriminalCase criminalCase);
    List<CriminalCase> getAll();
    CriminalCase update(CriminalCase criminalCase);
    void deleteById(Long id);

    CriminalCase findById(Long id);

}
