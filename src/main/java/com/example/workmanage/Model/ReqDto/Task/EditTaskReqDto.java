package com.example.workmanage.Model.ReqDto.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTaskReqDto {
    private Long taskId;
    private String taskName;
    private String taskDiscription;
    private Date startDate;
    private Date endDate;
    private String taskStatus;
    private String taskPriority ;
    private String repeat ;
}
