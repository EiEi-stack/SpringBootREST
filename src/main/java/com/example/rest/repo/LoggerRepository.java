package com.example.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rest.bean.Logger;

public interface LoggerRepository extends JpaRepository<Logger, Integer> {
}
