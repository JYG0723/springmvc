package repository;

import model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

/**
 * @Author Nuc YongGuang Ji.
 * @Date 23:45 2017/9/3.
 * @Descrip
 */
@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {

    @Modifying
    @Transactional
    @Query("update BlogEntity blog set blog.title = :title,blog.content = :content,blog.userByUserId.id = :userId,blog.pubDate = :pubDate where blog.id = :blogId")
    public void updateBole(@Param("title") String title, @Param("content") String content, @Param("pubDate") Date pubDate,
                           @Param("userId") Integer userId, @Param("blogId") Integer id);
}
