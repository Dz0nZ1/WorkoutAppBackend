package rs.ac.singidunum.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.workout.models.auth.TokenModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenModel, Long> {

    @Query(value = """
      select t from TokenModel t inner join UserModel u\s
      on t.user.user_id = u.user_id\s
      where u.user_id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<TokenModel> findAllValidTokenByUser(Long id);

    Optional<TokenModel> findByToken(String token);

}
