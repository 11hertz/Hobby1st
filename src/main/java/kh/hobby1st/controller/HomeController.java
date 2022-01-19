package kh.hobby1st.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.hobby1st.dto.ClubListDTO;
import kh.hobby1st.service.ClubListService;
import kh.hobby1st.service.ClubMemberService;
import kh.hobby1st.service.MemberService;
import kh.hobby1st.service.MyPageService;


@Controller
public class HomeController {
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private ClubMemberService cService;
	
	@Autowired
	private ClubListService clService;
	
	@Autowired
	private MyPageService mpService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public String home(Model model) throws Exception {
		int memberCount = mService.totalMember();
		int clmemCount = cService.totalClubMember();
		int clCount = clService.countClub();
		List<ClubListDTO> map = clService.selectAll();

		model.addAttribute("list", map);
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("clmemCount", clmemCount);
		model.addAttribute("clCount", clCount);
		return "home";
	}
	
	// 메인페이지 이동
	@RequestMapping("fullpage")
	public String main() {
		return "fullpage/fullpage";
	}
	
	// myPage
	@RequestMapping("myPage")
	public String myPage(Model model) {
		if((String)session.getAttribute("mem_id") == null) {
			return "redirect:/";
		}
		String my_id = (String) session.getAttribute("mem_id");
	
		List<ClubListDTO> clubList_make = mpService.clubList_make(my_id);	// 내가 만든 동호회 리스트
		List<ClubListDTO> clubList_join = mpService.clubList_join(my_id);	// 내가 가입한 동호회 리스트
		List<ClubListDTO> clubList_interest = mpService.clubList_interest(my_id);	// 내가 관심있는 동호회 리스트
		
		
		model.addAttribute("clubList_make", clubList_make); 
		model.addAttribute("clubList_join", clubList_join);
		model.addAttribute("clubList_interest", clubList_interest);
		
		return "myPage/myPage";
	}
	
	// club 
	@RequestMapping("club")
	public String club() {
		if((String)session.getAttribute("mem_id") == null) {
			return "redirect:/";
		}
		return "club";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("예외 처리 코드가 실행되었습니다.");
		return "redirect:/";
	}
}
