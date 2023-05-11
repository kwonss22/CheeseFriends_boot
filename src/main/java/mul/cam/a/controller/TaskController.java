package mul.cam.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mul.cam.a.dto.ListParam;
import mul.cam.a.dto.TaskDto;
import mul.cam.a.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	TaskService service;
	
	@ResponseBody
	@GetMapping(value="tasklist")
	public Map<String, Object> tasklist (ListParam param) {
		System.out.println("TaskController tasklist()" + new Date());
		
		// 글의 시작과 끝 
		int pn = param.getPageNumber(); // 0 1 2 3 4
		int start = (pn * 10);
		int end = (pn + 1) * 10;
		
		param.setStart(start);
		param.setEnd(end);
		
		List<TaskDto> list = service.taskList(param);
		
		int len = service.getAllTask(param);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("cnt", len);	
		
		return map;
	}
	
	
	@ResponseBody
	@PostMapping(value="writeTask")
	public String writeTask(TaskDto bbs) {
		System.out.println("TaskController writeTask " + new Date());
		
		boolean b = service.writeTask(bbs);
		
		if(b == false) {
			return "NO";
		}
		
		return "YES";
	}
	
	@ResponseBody
	@GetMapping(value="getTask")
	public TaskDto getTask(Integer seq) {
		System.out.println("TaskController getTask " + new Date());
		
		return service.getTask(seq);
	}
}
