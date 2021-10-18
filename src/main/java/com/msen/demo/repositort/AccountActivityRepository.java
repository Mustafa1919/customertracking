package com.msen.demo.repositort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msen.demo.model.AccountActivity;

@Repository
public interface AccountActivityRepository extends JpaRepository<AccountActivity, Long>{

}
