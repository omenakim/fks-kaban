package com.fks.kanban.domain.repository;


import com.fks.kanban.domain.model.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, String> {

}