package kr.human.anihospital.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import kr.human.anihospital.service.ProtectorPageService;
import kr.human.anihospital.vo.AnimalHospitalVO;
import kr.human.anihospital.vo.DoctorInfoVO;
import kr.human.anihospital.vo.PagingVO;
import kr.human.anihospital.vo.ProAnimalListVO;
import kr.human.anihospital.vo.ProDiaMedicineVO;
import kr.human.anihospital.vo.ProDiagnosisVO;
import kr.human.anihospital.vo.ProMyPageDetailVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProtectorPageController {

	// 보호자 페이지 관련 Service
	@Autowired
	ProtectorPageService protectorPageService;

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 보호자,환자 정보,환자의 진료리스트를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@GetMapping("/proMypageDetail")
	public String ProMypageDetail(@RequestParam(defaultValue = "1") int c, @RequestParam(defaultValue = "10") int p,
			@RequestParam(defaultValue = "10") int b, Model model, HttpSession session) throws Exception {
		int seqMember = (int) session.getAttribute("seqMember");
		// ----------------------------------------------------------------------------
		// 보호자,환자 정보 화면에 넘겨주기 시작
		// ----------------------------------------------------------------------------
		List<ProMyPageDetailVO> proMyPageDetailVOList = null;
		// 데이터 그릇에 담기
		proMyPageDetailVOList = protectorPageService.selectAllProMypageVOList(seqMember);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("proMyPageDetailVOList", proMyPageDetailVOList);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectAllProMypageVOList 서비스에서 넘어온 값(컨트롤러) : {}", proMyPageDetailVOList);

		// ----------------------------------------------------------------------------
		// 환자의 진료리스트 화면에 넘겨주기 시작
		// ----------------------------------------------------------------------------
		List<ProMyPageDetailVO> proMyPageDianosisList = null;
		// 데이터 그릇에 담기
		proMyPageDianosisList = protectorPageService.selectAllProDiagnosisList(seqMember);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("proMyPageDianosisList", proMyPageDianosisList);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectAllProDiagnosisList 서비스에서 넘어온 값(컨트롤러) : {}", proMyPageDianosisList);

		// ----------------------------------------------------------------------------
		// 후기리스트를 화면에 넘겨주기 시작
		// ----------------------------------------------------------------------------
		PagingVO<ProDiagnosisVO> pagingVO = protectorPageService.selectAllPostscript(seqMember, c, p, b);
		// 데이터 그릇에 담기
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("postscriptList", pagingVO.getList());
		model.addAttribute("info", pagingVO.getInfo());
		model.addAttribute("pageList", pagingVO.getPageList());
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectAllPostscript 서비스에서 넘어온 값(컨트롤러) : {}", pagingVO.getList());

		return "proMypageDetail";
	}

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 넘어온 값을 보호자 수정 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@PostMapping("/proMypageEdit")
	public String proMypageEdit(@RequestParam Map<String, Object> proMyPagemap, Model model) {
		// 보호자 정보 화면에서 수정 화면으로 값이 넘어왔는지 로그로 확인
		log.info("proMypageDetail에서 넘어온 보호자 정보 : {}", proMyPagemap);
		// 넘어온 값을 보호자 정보 수정 페이지에 넘겨주기
		model.addAttribute("proMyPagemap", proMyPagemap);

		return "proMypageEdit";
	}

	// ----------------------------------------------------------------------------------------------------
	// 보호자 정보 페이지에서 넘어온 값을 환자 정보 수정 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@PostMapping("/proPatientInfoEdit")
	public String proPatientInfoEdit(@RequestParam Map<String, Object> proPatientmap, Model model) {
		// 보호자 정보 화면에서 수정 화면으로 값이 넘어왔는지 로그로 확인
		log.info("proMypageDetail에서 넘어온 환자 정보 : {}", proPatientmap);
		// 넘어온 값을 환자 정보 수정 페이지에 넘겨주기
		model.addAttribute("proPatientmap", proPatientmap);

		return "proPatientInfoEdit";
	}

	// ----------------------------------------------------------------------------------------------------
	// 보호자가 환자 정보를 수정하는 메서드
	// ----------------------------------------------------------------------------------------------------
	@PostMapping("/proPatientInfoEditOk")
	public String proPatientInfoEditOk(@RequestParam Map<String, Object> updatePatientMap, MultipartFile file,
			MultipartFile vidfile) throws Exception {
		// 보호자 정보 화면에서 수정 화면으로 값이 넘어왔는지 로그로 확인
		log.info("proPatientInfoEdit에서 넘어온 수정된 환자 정보 : {}", updatePatientMap, file, vidfile);
		protectorPageService.updateProPatient(updatePatientMap, file, vidfile);
		return "redirect:proMypageDetail";
	}

	// ----------------------------------------------------------------------------------------------------
	// 보호자가 환자 정보를 추가하는 메서드
	// ----------------------------------------------------------------------------------------------------
	@PostMapping("/proPatientAddOk")
	public String proPatientAdd(@RequestParam Map<String, Object> insertPatientMap, MultipartFile file,
			MultipartFile vidfile, HttpSession session) throws Exception {
		// 세션에서 보호자 seq를 가져와서 insertPatientMap에 넣기
		int seqMember = (int) session.getAttribute("seqMember");
		insertPatientMap.put("seqMember", seqMember);
		// proPatientAdd 페이지에서 받아온 값 확인
		log.info("proPatientAdd에서 넘어온 환자 insert 정보 : {} {} {}", insertPatientMap, file, vidfile);
		// proPatientAdd 페이지에서 받아온 환자 추가 정보를 넣어서 insert하기
		protectorPageService.insertProPatient(insertPatientMap, file, vidfile);
		return "redirect:proMypageDetail";
	}

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 진료내역 리스트를 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@GetMapping(value = "/proAllAnimalList")
	public String selectAllProAnimalListVO(@RequestParam(defaultValue = "1") int c,
			@RequestParam(defaultValue = "10") int p, @RequestParam(defaultValue = "10") int b, Model model,
			HttpSession session) throws Exception {
		// 화면에 넘길 데이터를 담을 그릇 준비
		int seqMember = (int) session.getAttribute("seqMember");
		PagingVO<ProAnimalListVO> pagingVO = protectorPageService.selectAllProAnimalListVO(seqMember, c, p, b);
		// 데이터 그릇에 담기
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("proAllAnimalList", pagingVO.getList());
		model.addAttribute("info", pagingVO.getInfo());
		model.addAttribute("pageList", pagingVO.getPageList());
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectAllProAnimalListVO 서비스에서 넘어온 값(컨트롤러) : {}", pagingVO);

		// animallookup에 띄울 값 표시 종료
		// ---------------------------------------------------------------------

		return "proAllAnimalList";
	}

	// ----------------------------------------------------------------------------------------------------
	// 한 명의 보호자에 따른 환자의 1개의 상세진료내역을 화면에 표시해줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@GetMapping("/proDiagnosis")
	public String selectOneProDiagnosisVO(@RequestParam Map<String, Object> diagnosisMap, Model model)
			throws Exception {
		log.info("proAllAnimalList에서 넘어온 seq들 : {}", diagnosisMap);
		// ----------------------------------------------------------------------------
		// 처방 목록을 제외한 진료내역 화면에 넘겨주기
		// ----------------------------------------------------------------------------
		ProDiagnosisVO proDiagnosisVO = new ProDiagnosisVO();
		// 데이터 그릇에 담기
		proDiagnosisVO = protectorPageService.selectOneProDiagnosisVO(diagnosisMap);
		// 받아온 데이터 화면에 넘겨주기
		model.addAttribute("proDiagnosisVO", proDiagnosisVO);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectOneProDiagnosisVO 서비스에서 넘어온 값(컨트롤러) : {}", proDiagnosisVO);
		// ----------------------------------------------------------------------------
		// 처방 목록을 화면에 넘겨주기
		// ----------------------------------------------------------------------------
		List<ProDiaMedicineVO> proDiaMedicineVOList = null;
		// 데이터 그릇에 담기
		proDiaMedicineVOList = protectorPageService.selectListProDiaMedicineVO(diagnosisMap);
		model.addAttribute("proDiaMedicineVOList", proDiaMedicineVOList);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectListProDiaMedicineVO 서비스에서 넘어온 값(컨트롤러) : {}", proDiaMedicineVOList);
		// ----------------------------------------------------------------------------
		// 후기를 화면에 넘겨주기
		// ----------------------------------------------------------------------------
		ProDiagnosisVO proDiagnosisVOPostscript = new ProDiagnosisVO();
		proDiagnosisVOPostscript = protectorPageService.selectOnePostScript(diagnosisMap);
		model.addAttribute("proDiagnosisVOPostscript", proDiagnosisVOPostscript);
		// 제대로 데이터가 담겨 있는지 로그에 찍어보기
		log.info("selectOnePostScript 서비스에서 넘어온 값(컨트롤러) : {}", proDiagnosisVOPostscript);

		return "proDiagnosis";
	}

	// ----------------------------------------------------------------------------------------------------
	// 후기 작성 페이지 보여주는 메서드
	// ----------------------------------------------------------------------------------------------------
	@GetMapping("/proPostscriptAdd")
	public String proPostscriptAdd(@RequestParam int seqDiagnosis, @RequestParam int seqAnimal, Model model,
			HttpSession session) {
		int seqMember = (int) session.getAttribute("seqMember");
		model.addAttribute("seqDiagnosis", seqDiagnosis);
		model.addAttribute("seqMember", seqMember);
		model.addAttribute("seqAnimal", seqAnimal);
		return "proPostscriptAdd";
	}

	// ----------------------------------------------------------------------------------------------------
	// 후기 수정 페이지 보여주는 메서드
	// ----------------------------------------------------------------------------------------------------
	@GetMapping("/proPostscriptEdit")
	public String proPostscriptAdd(@RequestParam int seqPostscript, @RequestParam int seqDiagnosis,
			@RequestParam int seqAnimal, Model model, HttpSession session) {
		int seqMember = (int) session.getAttribute("seqMember");
		model.addAttribute("seqPostscript", seqPostscript);
		model.addAttribute("seqDiagnosis", seqDiagnosis);
		model.addAttribute("seqMember", seqMember);
		model.addAttribute("seqAnimal", seqAnimal);
		return "proPostscriptEdit";
	}

	// ----------------------------------------------------------------------------------------------------
	// 보호자 스케줄 페이지
	// ----------------------------------------------------------------------------------------------------
	@GetMapping("/proSchedule")
	public String proSchedule(Model model) throws Exception {
		List<AnimalHospitalVO> animalHospitalList = null;
		animalHospitalList = protectorPageService.selectAllAnimalHospitalVO();
		model.addAttribute("animalHospitalList", animalHospitalList);
		return "proSchedule";
	}

	// ----------------------------------------------------------------------------------------------------
	// 보호자 스케줄 페이지에서 병원 이름을 받아 의사 목록을 띄워줄 메서드
	// ----------------------------------------------------------------------------------------------------
	@PostMapping("/proScheduleDoctorList")
	@ResponseBody
	public List<DoctorInfoVO> proScheduleDoctorList(@RequestParam String animalHospitalName) throws Exception {
		List<DoctorInfoVO> doctorList = null;
		doctorList = protectorPageService.selectAllDoctorName(animalHospitalName);
		return doctorList;
	}
}
