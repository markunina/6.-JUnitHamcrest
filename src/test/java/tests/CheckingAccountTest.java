package tests;

import Accounts.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountTest {

    CheckingAccount sut = new CheckingAccount(0);

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running CheckingAccountTest");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("CheckingAccountTest complete");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Test start");
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete");
    }

    @Test
    public void testPay() {
        //given
        int amount = 1000;

        //when
        boolean result = sut.pay(amount);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void testPrintMessage() {
        //given
        boolean status = true;
        String original = "Операция выполнена успешно";

        //when
        String result = sut.getStatus(status);

        //then
        assertEquals(original, result);
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testTransferTrue(Account account, int amount, int balance, boolean original) {
        //act
        sut.setBalance(balance);
        boolean result = sut.transfer(account, amount);

        //arrange
        assertEquals(original, result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new CheckingAccount(0), 1000, 1000, true),
                Arguments.of(new CheckingAccount(0), 1000, 2000, true),
                Arguments.of(new CheckingAccount(1000), 1000, 1000, true),
                Arguments.of(new CheckingAccount(1000), 1000, 0, false),
                Arguments.of(new CheckingAccount(1000), 1000, 500, false),
                Arguments.of(new CreditAccount(0), 1000, 1000, false),
                Arguments.of(new CreditAccount(0), 1000, 2000, false),
                Arguments.of(new CreditAccount(-1000), 1000, 3000, true),
                Arguments.of(new CreditAccount(-1000), 1000, 1000, true),
                Arguments.of(new CreditAccount(-1000), 1000, 0, false),
                Arguments.of(new SavingsAccount(0), 1000, 1000, true),
                Arguments.of(new SavingsAccount(0), 1000, 2000, true),
                Arguments.of(new SavingsAccount(1000), 1000, 1000, true),
                Arguments.of(new SavingsAccount(1000), 1000, 0, false),
                Arguments.of(new SavingsAccount(1000), 1000, 500, false)
        );
    }

    @Test
    public void testAddMoney() {
        //given
        sut.setBalance(0);
        int amount = 1000;

        //when
        boolean result = sut.addMoney(amount);

        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void testConstructor() {
        //given
        sut = new CheckingAccount(-1000);

        //when
        int result = sut.getBalance();

        //then
        assertEquals(result, 0);
    }

}
