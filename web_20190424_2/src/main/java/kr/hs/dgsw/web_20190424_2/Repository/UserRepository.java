package kr.hs.dgsw.web_20190424_2.Repository;

import kr.hs.dgsw.web_20190424_2.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
