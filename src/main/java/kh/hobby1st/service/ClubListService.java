package kh.hobby1st.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.hobby1st.dao.ClubListDAO;

@Service
public class ClubListService {


	@Autowired
	public ClubListDAO cldao; 
}
