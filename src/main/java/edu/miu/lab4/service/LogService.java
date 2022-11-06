package edu.miu.lab4.service;

import edu.miu.lab4.entity.dto.LogDto;
import edu.miu.lab4.repo.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface LogService {


    void save(LogDto applicationLog);

}
