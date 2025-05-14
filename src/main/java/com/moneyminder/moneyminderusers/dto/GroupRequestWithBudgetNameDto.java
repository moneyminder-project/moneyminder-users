package com.moneyminder.moneyminderusers.dto;

import com.moneyminder.moneyminderusers.models.GroupRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequestWithBudgetNameDto {

    private String id;
    private String budgetName;
    private String requestingUser;
    private String requestedUser;
    private LocalDate date;
    private Boolean accepted;

    public GroupRequestWithBudgetNameDto groupToWithBudgetName(GroupRequest groupRequest) {
        this.id = groupRequest.getId();
        this.requestingUser = groupRequest.getRequestingUser();
        this.requestedUser = groupRequest.getRequestedUser();
        this.date = groupRequest.getDate();
        this.accepted = groupRequest.getAccepted();

        return this;
    }

    public GroupRequest withBudgetNameToGroupRequest() {
        final GroupRequest groupRequest = new GroupRequest();

        groupRequest.setId(this.id);
        groupRequest.setRequestingUser(this.requestingUser);
        groupRequest.setRequestedUser(this.requestedUser);
        groupRequest.setDate(this.date);
        groupRequest.setAccepted(this.accepted);

        return groupRequest;
    }

}
