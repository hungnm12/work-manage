package com.example.workmanage.Service.ServiceImpl;

import com.example.workmanage.Exception.ResponseException;
import com.example.workmanage.Model.Entity.Task;
import com.example.workmanage.Model.ReqDto.AddTaskReqDto;
import com.example.workmanage.Model.ResDto.GeneralResponse;
import com.example.workmanage.Repository.TaskRepo;
import com.example.workmanage.Service.TaskService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;


    @Override
    public GeneralResponse addTask(AddTaskReqDto addTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException {

        Task task = new Task();
        task.setTaskDiscription(addTaskReqDto.getTaskDiscription());
        task.setTaskName(addTaskReqDto.getTaskName());
        task.setTaskStatus(addTaskReqDto.getTaskStatus());
        task.setTaskPriority(addTaskReqDto.isTaskPriority());
        task.setRepeat(addTaskReqDto.isRepeat());
        task.setStartDate(addTaskReqDto.getStartDate());
        task.setEndDate(addTaskReqDto.getEndDate());

        taskRepo.save(task);


        return new GeneralResponse("","","");
    }
}
