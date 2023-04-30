package com.boot.newzips.main;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.account.AccountUserService;
import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListAllDTO;
import com.boot.newzips.dto.MemberDTO;
import com.boot.newzips.dto.WolseListingDTO;
import com.boot.newzips.itemList.ItemListUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainRealtorController {

	@Resource
	private ItemListUserService itemListUserService;
	
	@Resource
	private AccountUserService accountUserService;

	@GetMapping("/newzips/realtor")
	public ModelAndView itemList_user(Model model, Principal principal) throws Exception {
		
		MemberDTO user = null;

        // String itemId = request.getParameter("itemId");

        ModelAndView mav = new ModelAndView();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", 0);
        params.put("end", 6);

        List<ListAllDTO> listing = itemListUserService.getreadDataAll(params);

        System.out.println(listing.size());

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
        mav.addObject("listing2", listing); // 전체 데이터를 뷰에 전달
        mav.addObject("wolse", wolseList); // 누적된 월세 데이터를 모델에 추가
        mav.addObject("junsae", junsaeList); // 누적된 전세 데이터를 모델에 추가
		mav.addObject("user", user);

        mav.setViewName("realtor/main_realtor");

        return mav;
    }

	
	
}
