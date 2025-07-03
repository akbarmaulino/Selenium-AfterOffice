package testng.demoQA;


import org.testng.Assert;
import org.testng.annotations.*;


public class TestFirst {
    public int x;

    @Test
    public void firstTest() throws InterruptedException {
        System.out.println("Hello World");
        x = 0;
        Thread.sleep(1000); // Simulate some processing time
        Assert.assertTrue(x == 0, "Berhasil");
        System.out.println(x);
    }

    @Test (dependsOnMethods = {"firstTest"})
    public void secondTest() throws InterruptedException {
        System.out.println("Hello World 2");
        x = 1;
        Thread.sleep(1000); // Simulate some processing time
        Assert.assertTrue(x==1, "Berhasil");
        System.out.println(x);
    }

    
    @Test (dependsOnMethods = {"firstTest", "secondTest"})
    public void ThridTest() throws InterruptedException {
        System.out.println("Hello World 3");
        x = 3;
        Thread.sleep(1000); // Simulate some processing time
        Assert.assertTrue(x == 3, "Berhasil");
        System.out.println(x);
    }
    
}
