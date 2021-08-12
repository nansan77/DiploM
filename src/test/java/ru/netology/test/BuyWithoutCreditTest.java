package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataBaseHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class BuyWithoutCreditTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void shouldOpenWeb() {
        DataBaseHelper.cleanDataBase();
        open("http://localhost:8080", MainPage.class);
        mainPage.buyWithoutCredit();

    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldApproveFirstCard() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.expectApprovalFromBank();
        val expected = DataHelper.getFirstCardExpectedStatus();
        val actual = DataBaseHelper.getStatusPaymentWithoutCredit();
        assertEquals(expected, actual);
    }

    @Test
    void shouldRejectSecondCard() {
        val cardNumber = DataHelper.getSecondCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.expectRejectionFromBank();
        val expected = DataHelper.getSecondCardExpectedStatus();
        val actual = DataBaseHelper.getStatusPaymentWithoutCredit();
        assertEquals(expected, actual);

    }

    @Test
    void shouldRejectDifferentCard() {
        val cardNumber = DataHelper.getCardNumberDifferent();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.expectRejectionFromBank();
    }

    @Test
    void shouldRejectEmptyCardNumber() {
        val cardNumber = DataHelper.getCardNumberEmpty();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectEmptyMonth() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectInvalidMonth() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getInvalidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitInvalidDuration();
    }

    @Test
    void shouldRejectZeroMonth() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getZeroMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitInvalidDuration();
    }

    @Test
    void shouldRejectEmptyYear() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getEmptyYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectInvalidYear() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getInvalidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitInvalidYear();
    }

    @Test
    void shouldRejectEmptyOwner() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getEmptyOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitNecessaryFillOutField();
    }

    @Test
    void shouldRejectInvalidOwner() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getInvalidOwner();
        val cvs = DataHelper.getValidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectEmptyCvs() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getEmptyCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectInvalidCvs() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvs = DataHelper.getInvalidCvs();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvs);
        paymentPage.waitInvalidFormat();
    }

}