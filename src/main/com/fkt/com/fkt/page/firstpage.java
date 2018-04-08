package com.fkt.com.fkt.page;

import com.fkt.base.Utilites;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class firstpage {

    @FindBy(xpath="//span[text()='Electronics']")
    public static WebElement elect;

    @FindBy(linkText="Mobiles")
    public static WebElement Mobile;



    public static void firstpagetest(){
        Utilites.Mousehower(elect);
        Utilites.Mouseclick(Mobile);

    }
}
