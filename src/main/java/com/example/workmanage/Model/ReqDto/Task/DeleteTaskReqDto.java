package com.example.workmanage.Model.ReqDto.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTaskReqDto {
    private Long taskId;
}
