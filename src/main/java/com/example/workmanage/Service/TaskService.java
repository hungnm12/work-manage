package com.example.workmanage.Service;

import com.example.workmanage.Exception.ResponseException;
import com.example.workmanage.Model.ReqDto.Task.*;
import com.example.workmanage.Model.ResDto.GeneralResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public interface TaskService {
    GeneralResponse addTask(AddTaskReqDto addTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException;

    GeneralResponse editTask(EditTaskReqDto editTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException;

    GeneralResponse deleteTask(DeleteTaskReqDto deleteTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException;

    GeneralResponse search(SearchTaskReqDto searchTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException;

    GeneralResponse getListOfTasks(GetListOfTasksReqDto getListOfTasksReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException;

    GeneralResponse exportTask(ExportTaskReqDto exportTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException, IOException;
}
