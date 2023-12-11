package com.example.workmanage.Repository;

import com.example.workmanage.Model.Entity.Task;
import com.example.workmanage.Model.ResDto.Task.SearchTaskResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
    Task findAllById(Long taskId);

    Task findAllByTaskName(String taskName);

    Task findAllByStartDateAndEndDate(Date startDate, Date endDate);
    @Query("SELECT t from Task  t where t.taskName  like ?1  ")
    List<SearchTaskResDto> search(@Param("keyword") String keyword);




}
