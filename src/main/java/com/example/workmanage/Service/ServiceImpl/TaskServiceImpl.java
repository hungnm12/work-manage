package com.example.workmanage.Service.ServiceImpl;

import com.example.workmanage.Exception.ResponseException;
import com.example.workmanage.Model.Entity.Task;
import com.example.workmanage.Model.ReqDto.Task.*;
import com.example.workmanage.Model.ResDto.Task.EditTaskResDto;
import com.example.workmanage.Model.ResDto.GeneralResponse;
import com.example.workmanage.Model.ResDto.Task.GetListOfTasksResDto;
import com.example.workmanage.Model.ResDto.Task.SearchTaskResDto;
import com.example.workmanage.Repository.TaskRepo;
import com.example.workmanage.Service.TaskService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        if (addTaskReqDto.getTaskPriority().equals("1") ){
            task.setTaskPriority(true);
        }
        else {
            task.setTaskPriority(false);
        }
        if (addTaskReqDto.getRepeat().equals("1")) {
            task.setRepeat(true);
        }
        else {
            task.setRepeat(false);
        }
        task.setStartDate(addTaskReqDto.getStartDate());
        task.setEndDate(addTaskReqDto.getEndDate());

        taskRepo.save(task);


        return new GeneralResponse("", "", "");
    }



    @Override
    public GeneralResponse editTask(EditTaskReqDto editTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException {

        Task task = taskRepo.findAllById(editTaskReqDto.getTaskId());
        task.setTaskName(editTaskReqDto.getTaskName());
        task.setTaskDiscription(editTaskReqDto.getTaskDiscription());
        task.setTaskStatus(editTaskReqDto.getTaskStatus());

        task.setEndDate(editTaskReqDto.getEndDate());
        task.setStartDate(editTaskReqDto.getStartDate());
        if (editTaskReqDto.getTaskPriority().equals("1") ){
            task.setTaskPriority(true);
        }
        else {
            task.setTaskPriority(false);
        }
        if (editTaskReqDto.getRepeat().equals("1")) {
            task.setRepeat(true);
        }
        else {
            task.setRepeat(false);
        }


        taskRepo.save(task);

        EditTaskResDto editTaskResDto = new EditTaskResDto();
        editTaskResDto.setTaskId(String.valueOf(task.getTaskId()));
        editTaskResDto.setTaskName(task.getTaskName());
        editTaskResDto.setTaskDiscription(task.getTaskDiscription());
        if (task.isTaskPriority() == true) {
            editTaskResDto.setTaskPriority("Important");
        }
        else {
            editTaskResDto.setTaskPriority("Normal");
        }
        editTaskReqDto.setTaskStatus(task.getTaskStatus());
        editTaskResDto.setStartDate(String.valueOf(task.getStartDate()));
        editTaskResDto.setEndDate(String.valueOf(task.getEndDate()));
        if (task.isRepeat() == true) {
            editTaskResDto.setRepeat("Yes");
        }
        else {
            editTaskResDto.setRepeat("No");
        }
        return new GeneralResponse("","",editTaskResDto);
    }

    @Override
    public GeneralResponse deleteTask(DeleteTaskReqDto deleteTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException {

        Task task = taskRepo.findAllById(deleteTaskReqDto.getTaskId());

        taskRepo.delete(task);

        return new GeneralResponse();
    }

    @Override
    public GeneralResponse search(SearchTaskReqDto searchTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException {

        List<Task> getTasks = taskRepo.findAll();
        List<SearchTaskResDto> searchTaskResDtos = taskRepo.search(searchTaskReqDto.getKeyword());
        for (Task task : getTasks) {
            SearchTaskResDto searchTaskResDto = new SearchTaskResDto();
            if (taskRepo.findAllByTaskName(task.getTaskName()).equals(searchTaskReqDto.getKeyword())) {
                searchTaskResDto.setTaskName(task.getTaskName());
                searchTaskResDto.setTaskPriority(String.valueOf(task.isTaskPriority()));
                searchTaskResDto.setTaskDiscription(task.getTaskDiscription());
                searchTaskResDto.setTaskStatus(task.getTaskStatus());
                searchTaskResDto.setTaskId(String.valueOf(task.getTaskId()));
                searchTaskResDto.setRepeat(String.valueOf(task.isRepeat()));
                searchTaskResDto.setStartDate(String.valueOf(task.getStartDate()));
                searchTaskResDto.setEndDate(String.valueOf(task.getEndDate()));
                searchTaskResDtos.add(searchTaskResDto);
            } else {
                return new GeneralResponse("","",searchTaskResDtos);
            }

        }


        return new GeneralResponse("","",searchTaskResDtos);
    }

    @Override
    public GeneralResponse getListOfTasks(GetListOfTasksReqDto getListOfTasksReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException {
        List<Task> getTasks = taskRepo.findAll();
        List<GetListOfTasksResDto> getListOfTasksResDtos = new ArrayList<>();
        for (Task task : getTasks) {
            GetListOfTasksResDto getListOfTasksResDto = new GetListOfTasksResDto();

            getListOfTasksResDto.setTaskName(task.getTaskName());
            getListOfTasksResDto.setTaskDiscription(task.getTaskDiscription());
            getListOfTasksResDto.setTaskPriority(String.valueOf(task.isTaskPriority()));
            getListOfTasksResDto.setId(String.valueOf(task.getTaskId()));
            getListOfTasksResDto.setRepeat(String.valueOf(task.isRepeat()));
            getListOfTasksResDto.setEndDate(String.valueOf(task.getEndDate()));
            getListOfTasksResDto.setStartDate(String.valueOf(task.getStartDate()));
            getListOfTasksResDto.setTaskStatus(task.getTaskStatus());

            getListOfTasksResDtos.add(getListOfTasksResDto);
        }
        return new GeneralResponse("","",getListOfTasksResDtos);
    }

    @Override
    public GeneralResponse exportTask(ExportTaskReqDto exportTaskReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException, IOException {


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tasks");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Task ID");
        headerRow.createCell(1).setCellValue("Task Name");
        headerRow.createCell(2).setCellValue("Task Description");
        headerRow.createCell(3).setCellValue("Start Date");
        headerRow.createCell(4).setCellValue("End Date");
        headerRow.createCell(5).setCellValue("Task Status");
        headerRow.createCell(6).setCellValue("Task Priority");
        headerRow.createCell(7).setCellValue("Repeat");

        int rowIdx = 1;
        List<Task> getTasks = taskRepo.findAll();
        for (Task task : getTasks) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(task.getTaskId());
            row.createCell(1).setCellValue(task.getTaskName());
            row.createCell(2).setCellValue(task.getTaskDiscription());
            row.createCell(3).setCellValue(task.getTaskStatus());
            row.createCell(4).setCellValue(task.isTaskPriority() ? "Important" : "Normal");
            row.createCell(5).setCellValue(task.isRepeat() ? "Yes" : "No");
            row.createCell(6).setCellValue(task.getStartDate().toString() != null ? task.getStartDate().toString() : "".toString());
            row.createCell(7).setCellValue((task.getEndDate().toString() != null ? task.getEndDate().toString() : "".toString()));
            System.out.println(row.getCell(0));
        }
        String folderPath = "save_excel";
        String filePath = folderPath + File.separator + "my-data.xlsx";
        File file = new File(filePath);
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        while (file.exists()) {
            filePath = folderPath + File.separator + "my-data" + String.valueOf(randomNumber) + ".xlsx";
            file = new File(filePath);
        }
        HttpServletResponse response = null;
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=my-data.xlsx");
        FileOutputStream outputStream = new FileOutputStream(filePath);
        //Write to output stream
        workbook.write(outputStream);

        outputStream.close();
        return new GeneralResponse();
    }


}
