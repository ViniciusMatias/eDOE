package com.edoe.api.repositories;

import com.edoe.api.entity.ItemRequired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRequiredRepository extends JpaRepository<ItemRequired, Long> {
}
