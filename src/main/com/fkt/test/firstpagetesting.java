package com.fkt.test;

import com.fkt.base.Utilites;
import com.fkt.com.fkt.page.firstpage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class firstpagetesting {

    @BeforeMethod
    public void Open() throws IOException {
        Utilites.Openbrowser();
    }

    @AfterMethod
    public void Close(){
        Utilites.Closebrowser();
    }

    @Test
    public void Testone() throws InterruptedException{
        firstpage obj = PageFactory.initElements(Utilites.driver,firstpage.class);
        obj.firstpagetest();
        Thread.sleep(30);
    }
}
