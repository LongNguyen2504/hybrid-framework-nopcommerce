package com.nopcommerce.data;
//class này chứa các classes tương ứng với package com.nopcommerce.user của tầng sourcefolder testcases
//Nghĩa là mỗi modules(business của dự án) sẽ nằm trong 1 class của sourcefolder testdata như thế này
public class UserData {
	//Có thể khai báo data test cho nhiều sub-class như bên dưới để quản lý hoặc tạo từng class riêng(từng class riêng tương ứng với 1 chức năng test chứ k phải 1 classtest)
/*	public class Register {
		
	}
	public class Login{
		
	}
	public class MyAccount{
		
	}
	public class Invoice{
		
	}*/
	public class FakeData{
		public static final String FIRST_NAME= "aUTOMATION";
		public static final String LAST_NAME= "FC";
		public static final String EMAIL= "aUTOMATION@gmail.com";
		public static final String PASSWORD= "123456";
		public static final String DATE= "10";
		public static final String MONTH= "August";
		public static final String YEAR= "1999";
	}

}
