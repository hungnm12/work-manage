package com.example.workmanage.Repository;

import com.example.workmanage.Model.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Long> {

}
