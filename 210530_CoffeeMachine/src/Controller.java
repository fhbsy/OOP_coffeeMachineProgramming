
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;

public class Controller {
	public Controller() {
	}

	private static List<Object> ingredient;
	private int totalInput;
	private static int productPrice;
	public static boolean isRequestFinished = false;

	public Boolean checkValidMoney(Object source) { //nu
		// TODO implement here
		return null;
	}
	
	public static int getTotalInput() { //nu
		// TODO implement here
		return UserPanel.currentInput;
	}

	public static void setTotalInput(int totalInput) { //nu
		// TODO implement here
		UserPanel.currentInput=totalInput;
	}

	public static void compareInputPrice(int total) { //nu
		// TODO implement here
		if(total<UserPanel.currentInput) {
			System.out.println(total+"<"+UserPanel.currentInput);
		}
	}
	
	public void addCustomerInput(int newInput) { //nu
		// TODO implement here
		UserPanel.currentInput+=newInput;
	}

	public void requestChange() { //nu
		// TODO implement here
		if(isRequestFinished) {
			System.out.println("잔돈 요청 가능");
		}
	}

	public static void requestProduct(String selection) {
		// TODO implement here
		//compareInputPrice(0);
		
		Thread stepLightThread = new Thread() {
			public void run() {
				try {
					isRequestFinished=false;
					System.out.println("================================================");
					System.out.println(selection + " 제품이 선택되었습니다.");
					System.out.println("================================================\n1단계");
					Thread.sleep(1000);
					
					CupManager.requestCup();
					
					System.out.println("컵 준비 완료!");
					
					Thread.sleep(1000);
					int water = WaterManager.requestWater();
					ingredient = DataManager.getIngredient(selection);
					IngredientManager.requestIngredient(ingredient);
					System.out.println("================================================\n2단계");
					UserPanel.readyLight2.setText("●");
					
					Thread.sleep(1000);
					Manufacture.mix(water, ingredient);
					UserPanel.readyLight3.setText("●");
					ImageIcon imageIcon_full = new ImageIcon(new ImageIcon("fullCoffee.png").getImage()
							.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
					UserPanel.coffeeCupLabel.setIcon(imageIcon_full);
					System.out.println("================================================\n3단계");
					System.out.println("제조 완성! 음료를 가져가십시오.");
					System.out.println("================================================");
					System.out.println("요청을 기다리는 중 ...");
					
					Thread.sleep(1000);
					UserPanel.readyLight1.setText("O");
					UserPanel.readyLight2.setText("O");
					UserPanel.readyLight3.setText("O");
					ImageIcon imageIcon_empty = new ImageIcon(new ImageIcon("emptyCoffee.jpg").getImage()
							.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
					UserPanel.coffeeCupLabel.setIcon(imageIcon_empty);
					isRequestFinished=true;
					UserPanel.repaintCoffeeButton();
				}
				
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		stepLightThread.start();
		
		//Controller.productPrice = DataManager.getPrice(selection);
		//int change = MoneyManager.calculateChange(getTotalInput(), Controller.productPrice);
		//setTotalInput(change);
		//UserPanel.showMoneyLED(getTotalInput());
		//compareInputPrice(getTotalInput());
		countSetText();	
	}

	public static void countSetText() { 
		// TODO implement here
		System.out.println("금액 화면을 수정합니다.");
	}

	public void countUpdate() { //nu
		// TODO implement here
		System.out.println("CoinStackTotal: "+MoneyManager.getCoinStackTotal());
	}

}