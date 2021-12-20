package ChangeUser;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;

public class MainAddPass {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Nhap ID: ");
			int userID = sc.nextInt();
			sc.nextLine();
			System.out.println("Nhap password: ");
			String password = sc.nextLine();
			byte[] salt = CreatePass.getSalt();
			String passwordHash = CreatePass.get_SHA_512_SecurePassword(password, salt);
			String strSalt = Base64.getEncoder().encodeToString(salt);
			
			AddPassword.addNewPassword(userID, passwordHash, strSalt);
		}
		System.out.println("Them thanh cong");
	}

}
