package com.fakedc.practiceboard.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.fakedc.practiceboard.domain.Post;
import com.fakedc.practiceboard.domain.params.PostFilterParams;

@Mapper
public interface PostMapper {

	@Select({
			"<script>",
			"SELECT * FROM post WHERE board_id = #{ boardId } and post_type = #{ postType } ",
			"<choose>",
			"  <when test=\"filterType.toString() == 'TITLE' and keyword != null\">",
			"    and title like CONCAT('%', #{ keyword }, '%')",
			"  </when>",
			"  <when test=\"filterType.toString() == 'CONTENT' and keyword != null\">",
			"    and content like CONCAT('%', #{ keyword }, '%')",
			"  </when>",
			"  <when test=\"filterType.toString() == 'WRITER' and keyword != null and keyword != ''\">",
			"    and created_by = #{ keyword }",
			"  </when>",
			"  <when test=\"filterType.toString() == 'TITLE' and keyword != null\">",
			"    and (title like CONCAT('%', #{ keyword }, '%') or content CONCAT('%', #{ keyword }, '%'))",
			"  </when>",
			"  <otherwise>",
			"    and (title like CONCAT('%', #{ keyword }, '%') or content like CONCAT('%', #{ keyword }, '%')",
			"    <if test=\"keyword != null and keyword != ''\">",
			"       or created_by = #{ keyword }",
			"    </if>)",
			"  </otherwise>",
			"</choose>",
			" order by id desc",
			" limit #{ offset }, #{ pageSize }",
			"</script>"})
	@Results({
		@Result(column = "id", property = "id", id = true),
		@Result(column = "board_id", property = "boardId"),
		@Result(column = "title", property = "title"),
		@Result(column = "content", property = "content"),
		@Result(column = "password", property = "password"),
		@Result(column = "post_type", property = "postType"),
		@Result(column = "view_count", property = "viewCount"),
		@Result(column = "recommend_count", property = "recommendCount"),
		@Result(column = "unrecommend_count", property = "unrecommendCount"),
		@Result(column = "created_by", property = "createdBy"),
		@Result(column = "created_datetime", property = "createdDateTime"),
		@Result(column = "updated_datetime", property = "updatedDateTime")
	})
	List<Post> findByPostFilter(PostFilterParams filter);

	
	@Select({"<script>",
		"SELECT COUNT(*) FROM post WHERE board_id = #{ boardId } and post_type = #{ postType } ",
		"<choose>",
		"  <when test=\"filterType.toString() == 'TITLE' and keyword != null\">",
		"    and title like CONCAT('%', #{ keyword }, '%')",
		"  </when>",
		"  <when test=\"filterType.toString() == 'CONTENT' and keyword != null\">",
		"    and content like CONCAT('%', #{ keyword }, '%')",
		"  </when>",
		"  <when test=\"filterType.toString() == 'WRITER' and keyword != null and keyword != ''\">",
		"    and created_by = #{ keyword }",
		"  </when>",
		"  <when test=\"filterType.toString() == 'TITLE' and keyword != null\">",
		"    and (title like CONCAT('%', #{ keyword }, '%') or content CONCAT('%', #{ keyword }, '%'))",
		"  </when>",
		"  <otherwise>",
		"    and (title like CONCAT('%', #{ keyword }, '%') or content like CONCAT('%', #{ keyword }, '%')",
		"    <if test=\"keyword != null and keyword != ''\">",
		"       or created_by = #{ keyword }",
		"    </if>)",
		"  </otherwise>",
		"</choose>",
		"</script>"
	})
	int getAllPostCount(PostFilterParams filter);
	
}
