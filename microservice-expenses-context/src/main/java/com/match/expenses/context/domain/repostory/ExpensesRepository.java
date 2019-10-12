package com.match.expenses.context.domain.repostory;

import com.match.expenses.context.domain.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * Created by jagua on 2019/5/27.
 */
@Repository
public interface ExpensesRepository extends JpaRepository<Expense,String>, JpaSpecificationExecutor<Expense> {


    @Query(value = "from Expense where groupId =:groupId and date_format(consumTime,'%Y-%m') =:time order by consumTime desc",
            countQuery = "select count(*) from Expense where groupId =:groupId and date_format(consumTime,'%Y-%m') =:time")
    Page<Expense> findAll(@Param("groupId") String groupId,@Param("time")String time, Pageable pageable);

}
