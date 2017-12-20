package net.xueshupa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.NumberUtil;

import net.xueshupa.entity.Chapter;

@Service("chapterService")
public class ChapterServiceImpl implements ChapterService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;

	@Override
	public void deleteByCourseCode(String code,String operator){
		String sql = "DELETE FROM Chapter WHERE code LIKE CONCAT('#{code}','%')";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("code", code);
		commonMapper.deleteAndBak(sql, Chapter.class, params, operator);
	}

	@Override
	public Chapter saveChapter(Chapter chapter,String courseCode){
		if(chapter.getId()!=null){
			commonMapper.insert("Chapter.updateChapter", chapter);
			return (Chapter) commonMapper.get(Chapter.class, chapter.getId());
		}
		else{
			Integer orderIndex = (Integer) commonMapper.get("Chapter.selectMaxOrderIndexByCode",courseCode);
			if(orderIndex==null){
				orderIndex = 1;
			}
			else{
				orderIndex++;
			}
			chapter.setOrderIndex(orderIndex);
			String code = courseCode + NumberUtil.toZeroCode(orderIndex, 3);
			chapter.setCode(code);
			commonMapper.insert("Chapter.insertChapter", chapter);
			
			String sql = "UPDATE Course SET chapterCount = (SELECT COUNT(id) FROM Chapter WHERE courseId = #{courseId}) WHERE id = #{courseId}";
			commonMapper.updateBySql(sql, chapter);
			return chapter;
		}
	}
	
	@Override
	public List<Chapter> selectListByCourseId(Integer courseId){
		Chapter chapter = new Chapter();
		chapter.setCourseId(courseId);
		return commonMapper.selectList(Chapter.class, chapter);
	}

	@Override
	public List<Chapter> selectListByCourseCode(String courseCode){
		return commonMapper.selectList("Chapter.selectListByCourseCode", courseCode);
	}

	@Override
	public Chapter getByCode(String code){
		return commonMapper.get(Chapter.class, "code", code);
	}
	
	@Override
	public Chapter getById(Integer id) {
		return commonMapper.get(Chapter.class, id);
	}
	
	@Override
	public void deleteChapter(Integer id,String operator) {
		commonMapper.deleteAndBak(Chapter.class, id, operator);
	}
	
	@Override
	public List<Chapter> selectListByIdList(List<Integer> chapterIdList){
		return commonMapper.selectList("Chapter.selectListByIdList",chapterIdList);
	}
}
