package kr.human.anihospital.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.human.anihospital.vo.AnimalHospitalVO;
import kr.human.anihospital.vo.DoctorInfoVO;
import kr.human.anihospital.vo.PagingVO;
import kr.human.anihospital.vo.ProAnimalListVO;
import kr.human.anihospital.vo.ProDiaMedicineVO;
import kr.human.anihospital.vo.ProDiagnosisVO;
import kr.human.anihospital.vo.ProMyPageDetailVO;

public interface ProtectorPageService {
	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 보호자,환자 정보를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	List<ProMyPageDetailVO> selectAllProMypageVOList(int seqMember) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 환자의 진료리스트를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	List<ProMyPageDetailVO> selectAllProDiagnosisList(int seqMember) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 후기를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	PagingVO<ProDiagnosisVO> selectAllPostscript(int seqMember, int currentPage, int pageSize, int blockSize) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보를 수정하는 메서드
	// ----------------------------------------------------------------------------------------------------
	void updateProMypage(Map<String, Object> updateMap) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 보호자가 환자를 추가하는 메서드
	// ----------------------------------------------------------------------------------------------------
	void insertProPatient(Map<String, Object> insertPatientMap, MultipartFile animalPicture, MultipartFile animalVideo)
			throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 보호자가 환자 정보를 수정하는 메서드
	// ----------------------------------------------------------------------------------------------------
	void updateProPatient(Map<String, Object> updatePatientMap, MultipartFile animalPicture, MultipartFile animalVideo)
			throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	PagingVO<ProAnimalListVO> selectAllProAnimalListVO(int seqMember, int currentPage, int pageSize, int blockSize) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 1개의 상세진료 내역에서 후기를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	ProDiagnosisVO selectOnePostScript(Map<String, Object> diagnosisMap) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	ProDiagnosisVO selectOneProDiagnosisVO(Map<String, Object> diagnosisMap) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 상세진료 내역의 처방목록을 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	List<ProDiaMedicineVO> selectListProDiaMedicineVO(Map<String, Object> diagnosisMap) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 진료내역페이지에서 후기작성하는 메서드
	// ----------------------------------------------------------------------------------------------------
	void insertProPostScript(Map<String, Object> postScriptMap) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 진료내역페이지에서 후기 수정하는 메서드
	// ----------------------------------------------------------------------------------------------------
	void updatePostscript(Map<String, Object> updatePostScriptMap) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 진료내역페이지에서 후기 삭제하는 메서드
	// ----------------------------------------------------------------------------------------------------
	void deletePostscript(int seqPostscript) throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 보호자 스케줄 페이지에서 보여줄 병원 정보 표시 메서드
	// ----------------------------------------------------------------------------------------------------
	List<AnimalHospitalVO> selectAllAnimalHospitalVO() throws Exception;

	// ----------------------------------------------------------------------------------------------------
	// 보호자 스케줄 페이지에서 보여줄 의사 정보 표시 메서드
	// ----------------------------------------------------------------------------------------------------
	List<DoctorInfoVO> selectAllDoctorName(String animalHospitalName) throws Exception;

}
