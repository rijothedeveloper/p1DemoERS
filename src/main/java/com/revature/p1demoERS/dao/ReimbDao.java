package com.revature.p1demoERS.dao;

import com.revature.p1demoERS.model.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReimbDao extends JpaRepository<Reimbursement, Long> {




}
