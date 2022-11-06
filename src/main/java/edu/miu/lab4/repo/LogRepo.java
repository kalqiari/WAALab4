package edu.miu.lab4.repo;

import edu.miu.lab4.entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo extends CrudRepository<Log, Long> {
}
