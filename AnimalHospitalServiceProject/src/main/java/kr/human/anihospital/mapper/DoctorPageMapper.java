package kr.human.anihospital.mapper;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.human.anihospital.vo.DoctorInfoVO;
import kr.human.anihospital.vo.FeedVO;
import kr.human.anihospital.vo.MedicineInfoVO;
import kr.human.anihospital.vo.SupplementsVO;
import kr.human.anihospital.vo.DiagnosisInfoVO;
import kr.human.anihospital.vo.DocPatientInfoVO;

// #######################################################################
// ### 수의사 전용 페이지의 모든 CRUD를 담은 Mapper 클래스 파일입니다. ###
// #######################################################################
@Mapper
public interface DoctorPageMapper {
	// 세션으로부터 seqMember값을 받아 /doctorInfo 페이지에서 내 정보 조회(의사)하는 매퍼 메소드
	DoctorInfoVO selectOneDoctorInfoVO(int seqMember) throws SQLException;

	// /doctorInfoEdit 페이지에서 수정한 값으로 DB를 업데이트 하는 매퍼 메소드
	void updateOneDoctorInfoVO(DoctorInfoVO doctorInfoVO) throws SQLException;

	// 세션으로부터 seqDoctor값을 받아 해당 의료인의 진료기록 수를 조회하는 메소드
	int selectCountPatientInfoVOList(int seqDoctor) throws SQLException;
	
	// 세션으로부터 seqDoctor값을 받아 /patientInfoList 페이지에서 진료내역을 리스트로 조회하는 매퍼 메소드
	List<DocPatientInfoVO> selectAllPatientInfoVO(int seqDoctor, int startNo, int pageSize) throws SQLException;

	// /patientInfoList 페이지에서 진료내역을 클릭하여 /patientInfo 페이지로 이동할 때 seqAnimal 값을 받아
	// 환자 정보를 조회하는 매퍼 메소드
	DocPatientInfoVO selectOnePatientInfoVO(int seqAnimal) throws SQLException;

	// /patientInfoList 페이지에서 진료내역을 클릭하여 /patientInfo 페이지로 이동할 때 seqAnimal 값을 받아
	// 환자의 이전 진료내역을 조회하는 매퍼 메소드
	List<DiagnosisInfoVO> selectAllPatientDiaRecord(int seqAnimal) throws SQLException;

	// /patientInfo 페이지에서 '수정'버튼을 눌러 환자의 개인정보를 수정하는 매퍼 메소드
	void updateOnePatientInfo(DocPatientInfoVO docPatientInfoVO) throws SQLException;

	// diagnosisAdd 페이지에 의약품 정보를 출력하기 위한 매퍼 메소드
	List<MedicineInfoVO> selectAllMedicineInfo() throws SQLException;

	// diagnosisAdd 페이지에서 약을 처방할 때 약의 이름을 선택하면 그에 관한 정보를 출력하는 메소드
	MedicineInfoVO selectOneMedicineInfo(String medicineName) throws SQLException;

	// /diagnosisAdd 페이지에서 처방약정보를 제외한 진료정보를 insert하는 매퍼 메소드
	void insertOneDiagnosisInfo(DiagnosisInfoVO diagnosisInfoVO) throws SQLException;

	// 가장 최근에 저장된 auto_increment 값을 불러오는 매퍼 메소드
	int selectLastInsertIndex() throws SQLException;

	// 진료기록의 처방되는 약 정보를 insert하는 매퍼 메소드
	void insertOneDiagnosisMedicine(HashMap<String, Integer> hashMap) throws SQLException;

	// diagnosisAdd 페이지에서 추천사료 엑셀 파일을 추가해 넘어온 JSON파일을 insert하는 메퍼 메소드
	void insertFeedExcelUpload(FeedVO feedVO) throws Exception;
	
	// diagnosisAdd 페이지에서 추천사료 엑셀 파일을 추가해 넘어온 JSON파일을 insert하는 메퍼 메소드
	void insertSupplementsExcelUpload(SupplementsVO supplementsVO) throws Exception;
	
	// 검색창에 입력된 환자 이름에 해당하는 환자 정보를 조회하는 매퍼 메소드
	List<DocPatientInfoVO> selectOneAnimalPatientInfoListVO(Map<String, Object> pagingOnePatient) throws SQLException;
	
	// 세션으로부터 seqDoctor, animalNmae값을 받아 해당 의료인의 해당하는 환자의 진료기록 수를 조회하는 메소드
	int selectOneAnimalPatientInfoListVOList(Map<String, Object> countPagingOnePatient) throws SQLException;
}
