package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private SelenideElement buttonBuy = $("[class='button button_size_m button_theme_alfa-on-white']");
    private SelenideElement buttonBuyByCredit = $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").get(0);


    public PaymentPage buyWithoutCredit(){
        buttonBuy.click();
        return new PaymentPage();
    }

    public PaymentPage buyWithCredit(){
        buttonBuyByCredit.click();
        return new PaymentPage();
    }

}