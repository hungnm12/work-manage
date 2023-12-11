package com.example.workmanage.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Embeddable
public class LilTask {

    @Id
    private Long lilTaskId;

    @Column(name = "lil_task_name")
    private String lilTaskName;

    @Column(name = "step")
    private String step;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
