package com.boot.newzips.itemList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListAllDTO;
import com.boot.newzips.dto.WolseListingDTO;

@RestController
public class ItemListSearchController {
	
	@Resource
    private ItemListUserService itemListUserService;
	
	 @GetMapping(value = "/newzips/itemList_search")
	    public ModelAndView itemlist_search(Model model,@RequestParam("searchvalue1") String searchvalue1
	    		,@RequestParam("searchvalue2") String searchvalue2,@RequestParam("searchvalue3") String searchvalue3) throws Exception {
		 
		 ModelAndView mav = new ModelAndView();
	        
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("start", 0);
	        params.put("end", 6);
	        params.put("searchvalue1", searchvalue1);
	        params.put("searchvalue2", searchvalue2);
	        params.put("searchvalue3", searchvalue3);
	        
	        List<ListAllDTO> listing = itemListUserService.getsearchlist(params);
	        
	        for (ListAllDTO item : listing) {
	            String itemImagePath = "/assets/listing_images/" + item.getItemId() + "/2.png";
	            item.setItemImagePath(itemImagePath); // 해당 아이템의 이미지 경로를 DTO의 새로운 속성에 저장
	        }
	        model.addAttribute("listing", listing);
	        
	        

	        List<WolseListingDTO> wolseList = new ArrayList<>(); // 누적할 월세 리스트
	        List<JunsaeListingDTO> junsaeList = new ArrayList<>(); // 누적할 전세 리스트
	        
	        
	        for (ListAllDTO dto : listing) {
	            System.out.println(dto);
	            if ("월세".equals(dto.getYearly_monthly())) {
	                List<WolseListingDTO> wolse = itemListUserService.getread_wolse(dto.getItemId());
	                wolseList.addAll(wolse); // 월세 데이터를 누적하여 리스트에 추가
	            } else if ("전세".equals(dto.getYearly_monthly())) {
	                List<JunsaeListingDTO> junsae = itemListUserService.getread_junsae(dto.getItemId());
	                junsaeList.addAll(junsae); // 전세 데이터를 누적하여 리스트에 추가
	            }
	        }
	        
	        mav.addObject("listing", listing); // 전체 데이터를 뷰에 전달
	        mav.addObject("wolse", wolseList); // 누적된 월세 데이터를 모델에 추가
	        mav.addObject("junsae", junsaeList); // 누적된 전세 데이터를 모델에 추가
	       
	        
	        mav.setViewName("user/itemlist_user");
			
	        return mav;
	 }


	    @PostMapping(value = "/search")
	    public ModelAndView itemlist_search(Model model, @RequestParam("start") int start, 
	    		@RequestParam("end") int end,@RequestParam("searchvalue1") String searchvalue1
	    		,@RequestParam("searchvalue2") String searchvalue2,@RequestParam("searchvalue3") String searchvalue3) throws Exception {
	    	

	        //String itemId = request.getParameter("itemId");

	        ModelAndView mav = new ModelAndView("jsonView");
	        
	        System.out.println("==================================");
	        System.out.println("start: " + start);
	        System.out.println("end: " + end);
	        
	        System.out.println("searchvalue1: " + searchvalue1);
	        System.out.println("searchvalue2: " + searchvalue2);
	        System.out.println("searchvalue3: " + searchvalue3);
	        
	        
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("start", start);
	        params.put("end", end);
	        params.put("searchvalue1", searchvalue1);
	        params.put("searchvalue2", searchvalue2);
	        params.put("searchvalue3", searchvalue3);

	        List<ListAllDTO> search = itemListUserService.getsearchlist(params);
	        
	        System.out.println(search.size());
	        

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
	        
	        model.addAttribute("search", search); // 전체 데이터를 뷰에 전달
	        model.addAttribute("wolse", wolseList); // 누적된 월세 데이터를 모델에 추가
	        model.addAttribute("junsae", junsaeList); // 누적된 전세 데이터를 모델에 추가

	        mav.addObject("search", search); // 전체 데이터를 뷰에 전달
	        mav.addObject("wolse", wolseList); // 누적된 월세 데이터를 모델에 추가
	        mav.addObject("junsae", junsaeList); // 누적된 전세 데이터를 모델에 추가
	        
	        mav.setViewName("user/search_user");

	        return mav;
	        
	    }
	    

	}