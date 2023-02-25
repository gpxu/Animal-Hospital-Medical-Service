package kr.human.anihospital.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProDiagnosisVO {
	//-----------------------------------------------------------
	// 보호자에 따른 환자의 상세 진료페이지 정보를 가지고 있는 VO
	//-----------------------------------------------------------
	private int seqDiagnosis;
	private int seqMember;
	private int seqAnimal;
	private String diagnosisDate;
	private String animalHospitalName;
	private int seqDoctor;
	private String memberName;
	private String animalName;
	private String animalAge;
	private String animalType;
	private String animalSize;
	private String animalWeight;
	private String diagnosisSymptom;
	
	// 처방약은 여러개 이므로 List로 받아야 한다.
	private String medicineManufactureCompany;
	private String medicineName;
	private String medicineMedicationGuide;
	private String medicineSideEffect;
	
	private int seqPostscript;
	private Date postscriptUpdateDate;
	private String postscriptSatisfaction;
	private String postscriptContent;
}
