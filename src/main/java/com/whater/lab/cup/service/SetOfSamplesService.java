package com.whater.lab.cup.service;

import com.whater.lab.cup.dto.SetOfSamplesDto;

import java.util.List;

public class SetOfSamplesService {

    public List<SetOfSamplesDto> getAll() {
        return List.of();
    }

    public SetOfSamplesDto find(Long id) {
        return new SetOfSamplesDto();
    }
    
    public void addSetOfSamples(SetOfSamplesDto dto) {

    }

    public void updateSetOfSamples(SetOfSamplesDto dto) {

    }

    public void cancelSetOfSamples(Long id) {
        //todo: zmiana statusów sampli na failure (pamiętaj o komentarzu - dla spójności, set of sample może mięc reason a sample generyczny komunikat o canclowaniu) - świadomy bug
    }

    public void removeSetOfSamples(Long id) {

    }
}
