package com.example.workmanage.Model.ReqDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddTaskReqDto {
    private String taskName;
    private String taskDiscription;
    private Date startDate;
    private Date endDate;
    private String taskStatus;
    private boolean taskPriority = false;
    private boolean repeat = false;
}
