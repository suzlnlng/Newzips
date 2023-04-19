package com.boot.newzips.itemList;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boot.newzips.dto.JunsaeListingDTO;
import com.boot.newzips.dto.ListAllDTO;

import com.boot.newzips.dto.WolseListingDTO;

@RestController
public class ItemListUserController {

    @Resource
    private itemListUserService itemListUserService;

    @GetMapping("/newzips/itemList_user")
    public ModelAndView itemList_user(HttpServletRequest request) throws Exception {

        String itemId = request.getParameter("itemId");

        ModelAndView mav = new ModelAndView();

        List<ListAllDTO> listing = itemListUserService.getreadDataAll();
        mav.addObject("listing", listing); // 전체 데이터를 뷰에 전달
        
        System.out.println(listing.size());
        
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
        
        mav.addObject("wolse", wolseList); // 누적된 월세 데이터를 모델에 추가
        mav.addObject("junsae", junsaeList); // 누적된 전세 데이터를 모델에 추가
       

        mav.setViewName("user/itemlist_user");

        return mav;
    }
    
    @GetMapping("/listing-data")
    public List<ListAllDTO> getListingData() throws Exception {
        List<ListAllDTO> listing = itemListUserService.getreadDataAll();
        return listing;
        
    }

}
