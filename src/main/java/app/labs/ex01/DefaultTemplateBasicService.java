package app.labs.ex01;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class DefaultTemplateBasicService implements TemplateBasicService{
	private List<String> list;
	private Map<String, String> map;
	private UserAccount userAccount;
	
	public DefaultTemplateBasicService() {
		// 이렇게 초기화하는 방식은 java9부터 가능함.
		this.list = List.of("A", "B", "C", "D", "E");
		this.map  = Map.of(
				"A", "가",
				"B", "나",
				"C", "다",
				"D", "라",
				"E", "마"
				);
		this.userAccount = new UserAccount("1", "user", "1234", "사용자", "ROLE_USER", null, "1");   
	}
	
	@Override
	public List<String> getList() {
		return this.list;
	}
	
	@Override
	public Map<String, String> getMap() {
		return this.map;
	}
	
	@Override
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

}
