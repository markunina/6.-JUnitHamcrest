package hamcrestTests;

import Accounts.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreditAccountTestHamcrest {
    CreditAccount sut = new CreditAccount(0);

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running CreditAccountTest");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("CreditAccountTest complete");
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
        assertThat(result, is(true));
    }

    @Test
    public void testPrintMessage() {
        //given
        boolean status = true;

        //when
        String result = sut.getStatus(status);

        //then
        assertThat(result, containsString("успешно"));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testTransferTrue(Account account, int amount, int balance, boolean original) {
        //act
        sut.setBalance(balance);
        boolean result = sut.transfer(account, amount);

        //arrange
        assertThat(result, equalTo(original));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new CheckingAccount(0), 1000, -1000, true),
                Arguments.of(new CheckingAccount(0), 1000, -2000, true),
                Arguments.of(new CheckingAccount(1000), 1000, -1000, true),
                Arguments.of(new CheckingAccount(1000), 1000, 0, true),
                Arguments.of(new CheckingAccount(1000), 1000, 500, true),
                Arguments.of(new CreditAccount(0), 1000, 1000, false),
                Arguments.of(new CreditAccount(0), 1000, 2000, false),
                Arguments.of(new CreditAccount(-1000), 1000, 3000, true),
                Arguments.of(new CreditAccount(-1000), 1000, 1000, true),
                Arguments.of(new CreditAccount(-1000), 1000, 0, true),
                Arguments.of(new SavingsAccount(0), 1000, 1000, true),
                Arguments.of(new SavingsAccount(0), 1000, 2000, true),
                Arguments.of(new SavingsAccount(1000), 1000, 1000, true),
                Arguments.of(new SavingsAccount(1000), 1000, 0, true),
                Arguments.of(new SavingsAccount(1000), 1000, 500, true)
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
        assertThat(result, not(true));
    }

    @ParameterizedTest
    @MethodSource("sourceTestConstructor")
    public void testConstructor(CreditAccount account) {
        //given
        sut = new CreditAccount(1000);

        //when
        int result = sut.getBalance();

        //then
        assertThat(result, Matchers.lessThanOrEqualTo(0));
    }

    private static Stream<Arguments> sourceTestConstructor() {
        return Stream.of(
                Arguments.of(new CreditAccount(-1000)),
                Arguments.of(new CreditAccount(-1)),
                Arguments.of(new CreditAccount(0)),
                Arguments.of(new CreditAccount(1000)));
    }

}
