package kr.human.anihospital.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.AllNoticeListVO;
import kr.human.anihospital.vo.InsertWriterInfoVO;

@Mapper
public interface NoticeMapper {
	//----------------------------------------------------------------------------------------------------
	// 모든 공지를 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	List<AllNoticeListVO> selectAllNoticeListVO() throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 공지 상세 화면 & 공지 수정 화면에 표시해줄 메서드
	//----------------------------------------------------------------------------------------------------
	AllNoticeListVO selectOneDetailNoticeVO(int seqNotice) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// insert화면에 로그인 한 의사 정보 뿌려줄 메서드
	//----------------------------------------------------------------------------------------------------
	InsertWriterInfoVO selectInsertNoticeWriterInfo(int seqDoctor, int seqAnimalHospital) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 공지를 추가해줄 메서드
	//----------------------------------------------------------------------------------------------------
	void insertOneNotice(Map<String, String> insertNoticeMap) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 공지 수정 및 수정 내용을 화면에 돌려줄 메서드
	//----------------------------------------------------------------------------------------------------
	void updateOneNotice(Map<String, String> updateNoticeMap) throws Exception;
	
	//----------------------------------------------------------------------------------------------------
	// 공지를 삭제해줄 메서드
	//----------------------------------------------------------------------------------------------------
	void deleteOneNotice(int seqNotice) throws Exception;
}
