package pruebas;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {				
			public static void main(String[] args) {									
            Result result = JUnitCore.runClasses(LoadTestsfallos.class);					
			for (Failure failure : result.getFailures()) {							
              System.out.println(failure.toString());}
            Result result1 = JUnitCore.runClasses(LoadTest.class);					
  			for (Failure failure1 : result1.getFailures()) {							
                System.out.println(failure1.toString());}
  			Result result2 = JUnitCore.runClasses(ShootingTest1.class);					
  			for (Failure failure2 : result2.getFailures()) {							
                System.out.println(failure2.toString());}
  			Result result3 = JUnitCore.runClasses(ShootingTest2.class);					
  			for (Failure failure3 : result3.getFailures()) {							
                System.out.println(failure3.toString());}
  			Result result4 = JUnitCore.runClasses(ColocarTest.class);					
  			for (Failure failure4 : result4.getFailures()) {							
                System.out.println(failure4.toString());}
  			Result result5 = JUnitCore.runClasses(InicializaryJugadorAutomaticoTest.class);					
  			for (Failure failure5 : result5.getFailures()) {							
                System.out.println(failure5.toString());}
  			Result result6 = JUnitCore.runClasses(ShootingTestAsiMismo.class);					
  			for (Failure failure6 : result6.getFailures()) {							
                System.out.println(failure6.toString());}
  	  if(result.wasSuccessful() && 
    		  result1.wasSuccessful() && 
    		  result2.wasSuccessful() && 
    		  result3.wasSuccessful() && 
    		  result4.wasSuccessful() && 
    		  result5.wasSuccessful() && 
    		  result6.wasSuccessful()) {
    	  System.out.println("\n\n\n\n//////////////////////////////////////Pasa todas las Pruebas//////////////////////////////////////");
      }
      else {
    	  System.out.println("LoadTestsfallos=="+result.wasSuccessful());
          System.out.println("LoadTest=="+result1.wasSuccessful());
          System.out.println("ShootingTest1=="+result2.wasSuccessful());
          System.out.println("ShootingTest2=="+result3.wasSuccessful());
          System.out.println("ColocarTest=="+result4.wasSuccessful());
          System.out.println("InicializaryJugadorAutomatico=="+result5.wasSuccessful());
          System.out.println("ShootingTestAsiMismo=="+result6.wasSuccessful());
    	  System.out.println("\n\n\n\n//////////////////////////////////////Hay fallos en las pruebas que ponen false//////////////////////////////////////");
      }
   }	
}
