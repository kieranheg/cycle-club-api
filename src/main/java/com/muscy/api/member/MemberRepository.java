package com.muscy.api.member;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberDao, Long> {
}

