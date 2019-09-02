package com.match.expenses.context.domain.repostory;

import com.match.expenses.context.domain.entity.GroupCluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by jagua on 2019/5/27.
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupCluster,String>, JpaSpecificationExecutor {


}
