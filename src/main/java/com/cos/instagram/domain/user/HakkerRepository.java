package com.cos.instagram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface HakkerRepository  extends JpaRepository<Haker, Integer>
{

}
