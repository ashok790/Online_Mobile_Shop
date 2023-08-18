package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.GroupOrder;


@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrder,Integer> {

}
