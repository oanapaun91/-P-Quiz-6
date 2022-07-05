package com.example.greatreads.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApprovedStatus {
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private String status;

    public void setStatus(String status){
        this.status = status;
    }
}
