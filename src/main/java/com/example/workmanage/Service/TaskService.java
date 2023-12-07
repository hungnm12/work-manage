package com.example.workmanage.Service;

import com.example.workmanage.Exception.ResponseException;
import com.example.workmanage.Model.ReqDto.AddTaskReqDto;
import com.example.workmanage.Model.ResDto.GeneralResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public interface TaskService {
    GeneralResponse addTask(AddTaskReqDto addTaskReqDto)throws ResponseException, ExecutionException, InterruptedException, TimeoutException;
}
