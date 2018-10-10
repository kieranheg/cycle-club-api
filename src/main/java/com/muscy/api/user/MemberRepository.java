package com.muscy.api.user;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberDao, Long> {
}

