package com.whater.lab.cup.controller;

import com.whater.lab.cup.dto.SetOfSamplesDto;
import com.whater.lab.cup.service.SetOfSamplesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SetOfSamplesController {

    private SetOfSamplesService setOfSamplesService;

    @GetMapping("/set-of-samples/{id}")
    public SetOfSamplesDto find(@PathVariable Long id) {
        return setOfSamplesService.find(id);
    }
}
