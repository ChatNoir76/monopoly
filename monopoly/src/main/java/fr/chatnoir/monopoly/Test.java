package fr.chatnoir.monopoly;

import java.net.URL;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		test.getImage();
	}
	
	public void getImage() {
		URL image = getClass().getResource("/images/plateau.jpg");
		System.out.println("test image : " + image.getFile());
	}

}
