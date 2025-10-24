package utils;
public class calculateDuty {

	
	public static long calculatepayableDuty(String amount)
	{
		double duty;
		double vehicleValue = Double.parseDouble(amount);
		
		if (vehicleValue <= 44999) {
			  duty = (vehicleValue / 100) * 3;
        } else {
        	duty = 1350 + ((vehicleValue - 45000) / 100) * 5;
            
            
        }
		   long rounded = Math.round(duty);
           return rounded;
		
	}
	
}
