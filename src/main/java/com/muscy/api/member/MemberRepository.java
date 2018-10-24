package com.muscy.api.member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<MemberDao, Long> {
    
    Optional<MemberDao> findByLastName(@Param("lastName") String lastName);
}

