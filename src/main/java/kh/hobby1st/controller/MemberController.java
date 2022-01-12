package kh.hobby1st.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {
   
	@Autowired
	private HttpSession session;
	
	// Navi ���� �α����� ������ sign_in.jsp �� �̵�
	@RequestMapping("sign_in")
	public String sign_in() {
		System.out.println("member/sign_in �Դϴ�.");
		return "member/sign_in";
	}
	
	// �α��� ����� ���� �Ȼ�Ƚ��ϴ�. 
	@RequestMapping("login")
	public String login(String login_id, String login_pass) {
		System.out.println("�Է��� ���̵� : " + login_id);
		System.out.println("�Է��� ��й�ȣ : " + login_pass);
		
        session.setAttribute("login_id", login_id);
		return "redirect: /";
	}
	
	@RequestMapping("sign_up")
	public String sign_up() {
		System.out.println("sign_up �Դϴ�.");
		return "member/sign_up";
	}
	
	@RequestMapping("logout")
	public String logout() {
		System.out.println("�α׾ƿ��Դϴ�");
		session.invalidate();
		return "redirect: /";
	}
}
