package com.boot.newzips.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlPageController {

	@GetMapping("/newzips/home1")
	public String home1() {
		
		return "01_Home";
	}
	
	@GetMapping("/newzips/home1-1")
	public String home11() {
		
		return "01_Home.rtl";
	}


@GetMapping("/newzips/home2")
public String home2() {
	
	return "02_Home";
}

@GetMapping("/newzips/home2-2")
public String home22() {
	
	return "02_Home.rtl";
}


@GetMapping("/newzips/home3")
public String home3() {
	
	return "03_Home";
}

@GetMapping("/newzips/home3-3")
public String home33() {
	
	return "03_Home.rtl";
}

@GetMapping("/newzips/home4")
public String home4() {
	
	return "04_Home";
}

@GetMapping("/newzips/home4-4")
public String home44() {
	
	return "04_Home.rtl";
}

@GetMapping("/newzips/home5")
public String home5() {
	
	return "05_Home";
}

@GetMapping("/newzips/home5-5")
public String home55() {
	
	return "05_Home.rtl";
}


@GetMapping("/newzips/home6")
public String home6() {
	
	return "06_Home";
}

@GetMapping("/newzips/home6-6")
public String home66() {
	
	return "06_Home.rtl";
}

@GetMapping("/newzips/home7")
public String home7() {
	
	return "07_Home_Categories_and_Advanced_Search";
}

@GetMapping("/newzips/home7-7")
public String home77() {
	
	return "07_Home_Categories_and_Advanced_Search.rtl";
}


@GetMapping("/newzips/home10")
public String home10() {
	
	return "10_About";
}

@GetMapping("/newzips/home10-1")
public String home101() {
	
	return "10_About.rtl";
}


@GetMapping("/newzips/home11")
public String home111() {
	
	return "11_Agent_Profile";
}


@GetMapping("/newzips/home11-1")
public String home1111() {
	
	return "11_Agent_Profile";
}

@GetMapping("/newzips/home12")
public String home12() {
	
	return "12_Blog_Grid";
}


@GetMapping("/newzips/home12-1")
public String home121() {
	
	return "12_Blog_Grid.rtl";
}

@GetMapping("/newzips/home13")
public String home13() {
	
	return "13_Blog_Standart";
}

@GetMapping("/newzips/home13-1")
public String home131() {
	
	return "13_Blog_Standart.rtl";
}


@GetMapping("/newzips/home14")
public String home14() {
	
	return "14_Blog_Open";
}

@GetMapping("/newzips/home14-1")
public String home141() {
	
	return "14_Blog_Open.rtl";
}


@GetMapping("/newzips/home15")
public String home15() {
	
	return "15_Contact";
}

@GetMapping("/newzips/home15-1")
public String home151() {
	
	return "15_Contact.rtl";
}

@GetMapping("/newzips/home17")
public String home17() {
	
	return "17_Features_Example_Alt_Titlebar";
}

@GetMapping("/newzips/home17-1")
public String home171() {
	
	return "17_Features_Example_Alt_Titlebar.rtl";
}


@GetMapping("/newzips/home18")
public String home18() {
	
	return "18_Half_Map";
}

@GetMapping("/newzips/home18-1")
public String home181() {
	
	return "18_Half_Map.rtl";
}

@GetMapping("/newzips/home21")
public String home21() {
	
	return "21_List_Layout_With_Map";
}

@GetMapping("/newzips/home21-1")
public String home211() {
	
	return "21_List_Layout_With_Map.rtl";
}

@GetMapping("/newzips/home22")
public String home220() {
	
	return "22_List_Layout_With_Sidebar";
}

@GetMapping("/newzips/home22-2")
public String home2201() {
	
	return "22_List_Layout_With_Sidebar.rtl";
}


@GetMapping("/newzips/home24")
public String home24() {
	
	return "24_Property_Single";
}

@GetMapping("/newzips/home24-1")
public String home241() {
	
	return "24_Property_Single.rtl";
}



}

