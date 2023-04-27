package com.boot.newzips.itemList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListAllDTO;
import com.boot.newzips.dto.WolseListingDTO;

@RestController
public class ItemListSearchController {
	
	@Resource
    private itemListUserService itemListUserService;
	
	 @GetMapping(value = "/newzips/itemList_search")
	    public ModelAndView itemlist_search(Model model) throws Exception {
		 
		 ModelAndView mav = new ModelAndView();
	        
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("start", 0);
	        params.put("end", 6);
	        
	        List<ListAllDTO> search = itemListUserService.getSearchData(params);
	        
	        for (ListAllDTO item : search) {
	            String itemImagePath = "/assets/listing_images/" + item.getItemId() + "/2.png";
	            item.setItemImagePath(itemImagePath); // 해당 아이템의 이미지 경로를 DTO의 새로운 속성에 저장
	        }
	        model.addAttribute("search", search);
	        
	        

	        List<WolseListingDTO> wolseList = new ArrayList<>(); // 누적할 월세 리스트
	        List<JunsaeListingDTO> junsaeList = new ArrayList<>(); // 누적할 전세 리스트
	        
	        
	        for (ListAllDTO dto : search) {
	            System.out.println(dto);
	            if ("월세".equals(dto.getYearly_monthly())) {
	                List<WolseListingDTO> wolse = itemListUserService.getread_wolse(dto.getItemId());
	                wolseList.addAll(wolse); // 월세 데이터를 누적하여 리스트에 추가
	            } else if ("전세".equals(dto.getYearly_monthly())) {
	                List<JunsaeListingDTO> junsae = itemListUserService.getread_junsae(dto.getItemId());
	                junsaeList.addAll(junsae); // 전세 데이터를 누적하여 리스트에 추가
	            }
	        }
	        
	        mav.addObject("search", search); // 전체 데이터를 뷰에 전달
	        mav.addObject("wolse", wolseList); // 누적된 월세 데이터를 모델에 추가
	        mav.addObject("junsae", junsaeList); // 누적된 전세 데이터를 모델에 추가
	       
	        
	        mav.setViewName("user/itemlist_user");
			
	        return mav;
	 }

}
