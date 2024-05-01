package com.revature.p1demoERS.model;


import jakarta.persistence.*;

@Entity
@Table(name="reimbursements")
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long reimbId;

    private String description;

    private Double amount;

    private Status status;

    @ManyToOne(fetch =FetchType.LAZY , cascade = CascadeType.MERGE)
    @JoinColumn(name="userId")
    private User user;


    public Reimbursement() {
    }

    public Reimbursement(Long reimbId, String description, Double amount, User useId, Status status) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.user = useId;
        this.status = status;
    }

    public Long getReimbId() {
        return reimbId;
    }

    public void setReimbId(Long reimbId) {
        this.reimbId = reimbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User useId) {
        this.user = useId;
    }

    @Override
    public String toString() {
        return "reimbursement{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                ", useId=" + user +
                '}';
    }
}
