package io.funwork.authority.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.funwork.authority.domain.Authority;

/**
 * Created by urosaria on 2015. 11. 5..
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
