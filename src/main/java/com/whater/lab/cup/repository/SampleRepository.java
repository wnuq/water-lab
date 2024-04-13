package com.whater.lab.cup.repository;

import com.whater.lab.cup.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
