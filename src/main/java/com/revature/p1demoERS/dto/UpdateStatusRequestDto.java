package com.revature.p1demoERS.dto;

import com.revature.p1demoERS.model.Status;

public record UpdateStatusRequestDto(Long id, Status status) {

}
