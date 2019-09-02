package com.match.expenses.context.domain.repostory;

import com.match.expenses.context.domain.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jagua on 2019/5/27.
 */
@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember,String>, JpaSpecificationExecutor {


    public List<GroupMember> findFirstByUserId(String userId);

    public List<GroupMember> findFirstByGroupId(String groupId);

    GroupMember findFirstByUserIdAndDefaultGroup(String userId,int DefaultGroup);

    Boolean existsByGroupIdAndUserId(String groupId,String userId);

}
