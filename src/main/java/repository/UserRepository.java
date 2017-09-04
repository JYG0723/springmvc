package repository;

import model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Nuc YongGuang Ji.
 * @Date 17:57 2017/8/31.
 * @Descrip
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying  // 修改操作
    @Transactional // 加入事务控制
    @Query("update UserEntity ue set ue.nickname=:nickname,ue.firstName=:firstName,ue.lastName=:lastName,ue.password=:password where ue.id=:id")
    public void updateUser(@Param("nickname") String nickname, @Param("firstName") String firstName,
                           @Param("lastName") String lastName, @Param("password") String password,
                           @Param("id") Integer id);

}
