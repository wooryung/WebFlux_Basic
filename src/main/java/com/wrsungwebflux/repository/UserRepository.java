package com.wrsungwebflux.repository;

import com.wrsungwebflux.vo.UserVo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserVo, Long> {
}
