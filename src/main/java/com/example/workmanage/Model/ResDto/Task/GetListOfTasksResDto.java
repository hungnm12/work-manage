package com.example.workmanage.Model.ResDto.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListOfTasksResDto {
    private String Id;
    private String taskName;
    private String taskDiscription;
    private String startDate;
    private String endDate;
    private String taskStatus;
    private String taskPriority;
    private String repeat;
}
