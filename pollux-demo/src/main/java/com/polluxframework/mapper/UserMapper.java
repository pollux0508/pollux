package com.polluxframework.mapper;

import com.polluxframework.entity.User;
import com.polluxframework.paginator.entity.PageBounds;
import com.polluxframework.paginator.entity.PageList;
import org.springframework.stereotype.Component;

/**
 * @author zhumin0508
 * created in  2018/8/29 11:50
 * modified By:
 */
@Component
public interface UserMapper {
	/**
	 * 通过唯一标识获取用户信息
	 *
	 * @param user 用户对象（唯一标识有ID  USER_CODE LOGIN_NAME）
	 * @return 用户对象
	 */
	User getUserByUnique(User user);


	/**
	 * 分页获取用户信息
	 *
	 * @param user       用户查询字段
	 * @param pageBounds 分页情况
	 * @return 分页的用户列表信息
	 */
	PageList<User> getUserList(User user, PageBounds pageBounds);
}
