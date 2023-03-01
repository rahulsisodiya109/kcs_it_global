package com.kcs;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KCS_Test extends KCS {

	@BeforeClass
	public void setEnv1() {
		setEnv();
	}

	@Test
	public void test1() throws Exception {
		Select_From_Ahmedabad_To_Pune();
	}

	@Test
	public void test2() throws Exception {
		Select_Departure_date_as_1st_date_of_next_month();
	}

	@Test
	public void test3() throws Exception {
		Select_ADULTS2_CHILDREN1_INFANTS1();
	}

	@Test
	public void test4() throws Exception {
		Click_on_search();
	}

	@Test
	public void test5() throws Exception {
		Verify_TripType_FROM_TO_DEPART_PASSENGERS_CLASS();
	}

	@Test
	public void test6() throws Exception {
		Filter_By_One_Way_Price_and_validate_all_flight_prices_are_between_that_range();
	}

	@Test
	public void test7() throws Exception {
		Filter_by_Nonstop_and_validate_count();

	}
}
