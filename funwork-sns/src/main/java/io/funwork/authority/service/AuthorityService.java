package io.funwork.authority.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.funwork.authority.domain.Authority;
import io.funwork.authority.repository.AuthorityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by urosaria on 2015. 11. 11..
 */
@Service
public class AuthorityService {

  @Autowired
  private AuthorityRepository authorityRepository;

  /**
   * 권한 등록
   *
   * @param authority 글 등록시 넘어온 권한목록
   */
  @Transactional
  public Authority saveAuthority(Authority authority){

    authority = authorityRepository.save(authority);

    return authorityRepository.findOne(authority.getAuthNumber());
  }

  /**
   * 권한 조회
   *
   * @param authNumber 조회할 권한 일련번호
   */
  public Authority showAuthority(Long authNumber){
    return authorityRepository.findOne(authNumber);
  }

  /**
   * 권한 수정
   *
   * @param authNumber 수정대상인 권한 일련번호
   * @param authority 권한 목록 (수정)
   */
  @Transactional
  public Authority updateAuthority(Long authNumber, Authority authority) {

    Authority showAuthority = showAuthority(authNumber);
    showAuthority.setAuthList(authority.getAuthList());

    return showAuthority;
  }

}
