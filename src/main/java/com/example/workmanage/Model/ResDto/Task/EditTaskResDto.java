package com.example.workmanage.Model.ResDto.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditTaskResDto {
    private String taskId;
    private String taskName;
    private String taskDiscription;
    private String startDate;
    private String endDate;
    private String taskStatus;
    private String taskPriority ;
    private String repeat ;
}
