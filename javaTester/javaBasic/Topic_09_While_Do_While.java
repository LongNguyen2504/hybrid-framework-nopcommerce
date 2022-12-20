package javaBasic;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_09_While_Do_While {
	
	@Test
	public void TC_04_While() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Nhập số a : ");
		int a = scan.nextInt();
		System.out.println("Nhập số b : ");
		int b = scan.nextInt();
		
		
		while(a < b) {
			if(a % 3 == 0 && a % 5 == 0) {
				System.out.println("a : "+a);
				a++;
			}
			
		}
	}

}
